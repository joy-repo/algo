
# Step-by-Step Guide to Configuring Multiple Data Sources

**Scenario: Two Databases**
1.	Primary Database – datasource1
2.	Secondary Database – datasource2

## Step 1: Add Dependencies

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Step 2: Configure application.properties

```
# Primary DataSource (Main Database)
spring.datasource1.url=jdbc:mysql://localhost:3306/db1
spring.datasource1.username=root
spring.datasource1.password=root
spring.datasource1.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource1.jpa.hibernate.ddl-auto=update
spring.datasource1.jpa.show-sql=true

# Secondary DataSource (Another Database)
spring.datasource2.url=jdbc:mysql://localhost:3306/db2
spring.datasource2.username=root
spring.datasource2.password=root
spring.datasource2.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource2.jpa.hibernate.ddl-auto=update
spring.datasource2.jpa.show-sql=true

```

## Step 3: Configure Datasources

**Primary datasource:**

```java
package com.example.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Configuration
public class PrimaryDataSourceConfig {

    @Primary  // Marks this as the default data source
    @Bean(name = "dataSource1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
```

**Secondary DataSource:**

```java
package com.example.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
public class SecondaryDataSourceConfig {

    @Bean(name = "dataSource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
```


## Step 4. Define Entity Classes for Each Data Source

**Primary DataSource: db1**

```java
package com.example.entity.db1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
```

**Primary DataSource: db2**

```java
package com.example.entity.db2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
}
```

## Step 5. Configure EntityManagerFactory and TransactionManager for Each Data Source

**Each database needs an EntityManagerFactory and TransactionManager.** </br>

**Primary:**

```java

package com.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.repository.db1",
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1"
)
public class PrimaryDatabaseConfig {

    @Primary
    @Bean(name = "entityManagerFactory1")
    public EntityManagerFactory entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource1") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.entity.db1")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

```

**Secondary:**

```java
package com.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.repository.db2",
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2"
)
public class SecondaryDatabaseConfig {

    @Bean(name = "entityManagerFactory2")
    public EntityManagerFactory entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource2") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.entity.db2")
                .build();
    }

    @Bean(name = "transactionManager2")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
```

## Step 6: Define Repositories

**Primary:**

```java
package com.example.repository.db1;

import com.example.entity.db1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
```

**Secondary:**

```java
package com.example.repository.db2;

import com.example.entity.db2.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
```

## Step 8: Use the Repositories in a Service

```java
package com.example.service;

import com.example.entity.db1.User;
import com.example.entity.db2.Product;
import com.example.repository.db1.UserRepository;
import com.example.repository.db2.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public DatabaseService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
```