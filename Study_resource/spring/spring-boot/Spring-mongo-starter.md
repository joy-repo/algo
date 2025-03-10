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

## 3. MongoDb document

```java
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users")  // Collection name in MongoDB
public class User {
    
    @Id
    private String id;
    private String name;
    private int age;
}
```

## 4.  Create a Repository

```java
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByName(String name);  // Custom Query Method
}
```

## 5. Create a Service Layer

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
```

## 6. Create a REST Controller

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{name}")
    public User getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
}
```
