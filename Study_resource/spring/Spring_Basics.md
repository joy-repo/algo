## Table of Contents

1. [Types of Bean Injection](#Types-of-Bean-Injection)
2. [Types  Of Autowiring](#Types-Of-Autowiring)
3. [Different Ways for naming Bean](#Different-Ways-for-naming-a-Bean)




## Types of Bean Injection

### 1. Constructor Injection: preferred way

* Dependencies are injected via constructor parameters.
* This is the preferred way because it ensures that required dependencies are always provided.

```java
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUsers() {
        userRepository.findAll();
    }
}


```

```java
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public void findAll() {
        System.out.println("Fetching all users...");
    }
}
```

**NOTE: @Autowired is not needed if there is only ONE constructor.
Spring automatically detects and injects. (from Spring 4.3.1 onwards in Sprng Boot)**

### 2. Setter Injection: Used when the dependency is optional

* Dependencies are injected via setter methods.
* Used when the dependency is optional.

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService() {} // Default constructor required for setter injection

    // ✅ `@Autowired` is needed to tell Spring to inject UserRepository
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void getUsers() {
        userRepository.findAll();
    }
}
```

### 3. Field Injection (Not Recommended ❌)

 Disadvantages:
* Hard to test (you can’t mock dependencies easily).
* Less flexible (can’t use final fields).
* Tightly coupled (Spring framework is required).

```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;  // Field Injection

    public void getUsers() {
        userRepository.findAll();
    }
}
```

## Types Of Autowiring

### 1. No Autowiring (autowire="no") ❌

This is the default option in Spring. Dependencies must be manually injected using explicit bean configuration.

✔ Pros: Full control over dependency injection. </br>
❌ Cons: Requires manual configuration for every dependency.

```xml
<bean id="userService" class="com.example.UserService">
    <property name="userRepository" ref="userRepository"/>
</bean>

<bean id="userRepository" class="com.example.UserRepository"/>
```

### 2. By Type (autowire="byType") ✅

* If one bean of that type exists, Spring injects it.
* multiple beans of the same type exist, an error occurs unless you specify the correct one with @Qualifier.

```xml
<bean id="userService" class="com.example.UserService" autowire="byType"/>
<bean id="userRepository" class="com.example.UserRepository"/>
```


```java
@Service
public class UserService {
    @Autowired  // Injects based on type
    private UserRepository userRepository;
}
```

###  3. By Name (autowire="byName")

Spring injects the dependency by matching the bean name with the property name.

✔ Pros: Works well if the property name matches the bean name. </br>
❌ Cons: Renaming the property can break dependency injection.

```xml
<bean id="userService" class="com.example.UserService" autowire="byName"/>
<bean id="userRepository" class="com.example.UserRepository"/>
```

```java
@Service
public class UserService {
    @Autowired
    @Qualifier("userRepository")  // Matches bean name
    private UserRepository userRepository;
}
```

### 4. Constructor (autowire="constructor") ✅ (Best Practice)

Spring injects dependencies using the constructor parameter.
* The argument type should match a defined bean type.
* Recommended because it ensures required dependencies are always set.

✔ Pros: </br>
✅ Ensures dependencies are mandatory (prevents null). </br>
✅ Best for unit testing (easier to mock).</br>
❌ Cons: Requires more boilerplate code (constructor definition).


```xml
<bean id="userService" class="com.example.UserService" autowire="constructor"/>
<bean id="userRepository" class="com.example.UserRepository"/>
```

```java
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired  // (Not needed in Spring 4.3+)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

| Autowiring Type    |	How It Works | 	Best Use Case                           |	Recommended? |
|--------------------|---|------------------------------------------|---|
| No (autowire="no") |	No autowiring, manual config | 	Full control                            |	❌ No |
|By Type (autowire="byType") |	Matches property type| 	Single dependency per type              |	⚠️ Risky with multiple beans
|By Name (autowire="byName") |	Matches property name with bean name| 	Property name matches bean name         |	⚠️ Prone to renaming issues|
|Constructor (autowire="constructor")|	Injects via constructor params| 	Best practice for required dependencies | 	✅ Yes (Recommended)             
|Autodetect (autowire="autodetect")|	Tries constructor, then byType| 	Deprecated feature                      |	❌ No


## Different Ways for naming a Bean

### 1. Default Bean Name (Class Name in camelCase)

If no name is explicitly provided, Spring will use the class name with the first letter in lowercase as the bean name.
```java

import org.springframework.stereotype.Service;

@Service  // No name provided
public class UserService {
}
```
✔ Bean Name: userService
(Spring automatically assigns the name userService, derived from the class name.)

### 2. Using @Component, @Service, @Repository, @Controller with a Custom Name

```java
import org.springframework.stereotype.Service;

@Service("customUserService")  // Custom bean name
public class UserService {
}
```

✔ Bean Name: customUserService

### 3. Using @Bean in a Configuration Class

When using @Bean inside a @Configuration class, you can specify the bean name.

```java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean(name = "customBean")
    public UserService userService() {
        return new UserService();
    }
}
```

✔ Bean Name: customBean

### 4.  Using Multiple Names (@Bean or @Component)

```java
@Bean(name = {"userService", "primaryUserService", "mainUserService"})
public UserService userService() {
    return new UserService();
}
```

✔ Bean Names: userService, primaryUserService, mainUserService

### 6.  Using @Qualifier to Select a Specific Bean

If multiple beans of the same type exist, you can use @Qualifier to specify the exact bean name.

```java
@Service("primaryUserService")
public class UserService {
}

@Service("backupUserService")
public class BackupUserService {
}

@Component
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("primaryUserService") UserService userService) {
        this.userService = userService;
    }
}
```

✔ Selected Bean Name: primaryUserService

