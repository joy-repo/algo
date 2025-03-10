# Spring-Mongo-starter

## 1. Add Dependencies 

If you’re using Maven, add the following dependencies to your pom.xml:

```xml
<dependencies>
    <!-- Spring Boot Starter for MongoDB -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>

    <!-- Lombok (Optional, for reducing boilerplate code) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

## 2. Configure application.properties (or application.yml)

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/mydatabase
spring.data.mongodb.database=mydatabase
# if Autnentication
# spring.data.mongodb.uri=mongodb://username:password@localhost:27017/mydatabase
```

## 