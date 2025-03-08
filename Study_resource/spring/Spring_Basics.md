
### Types of Bean Injection

**1.  Constructor Injection : preferred way**

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

**2.  Setter Injection : Used when the dependency is optional.**

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

**3.  `Setter Injection : Used when the dependency is optional.`**