
# Step-by-Step Guide to Configuring Multiple Data Sources

**Scenario: Two Databases**
1.	Primary Database – datasource1
2.	Secondary Database – datasource2

## Step 1: Add Dependencies

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
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