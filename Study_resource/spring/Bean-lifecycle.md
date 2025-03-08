

## Bean Lifecycle Stages

A Spring bean follows these main steps:

1. Instantiation (Object Creation)
   * Spring creates a new instance of the bean using the constructor (new keyword or factory method).

2. Dependency Injection
   * Spring injects dependencies via constructor, setter, or field injection.

3. Post-Initialization (Custom Initialization)
   * Spring allows custom initialization using:
   * @PostConstruct
   * InitializingBean#afterPropertiesSet()
   * Custom init-method

4. Bean is Ready for Use
   * The bean is now fully initialized and available for use.

5. Pre-Destroy (Cleanup Before Shutdown)
   * Spring allows custom cleanup before destroying the bean:
   * @PreDestroy
   * DisposableBean#destroy()
   * Custom destroy-method

6. Destruction (Bean Removal)
   * The bean is removed from the application context when the container shuts down.

### 1. @PostConstruct (Initialization)

* This method runs **after dependency injection.**

```java
@Component
public class MyBean {
    @PostConstruct
    public void init() {
        System.out.println("🔹 MyBean initialized!");
    }
}
```

### 2. InitializingBean#afterPropertiesSet()

* Another way to define custom initialization logic.
* **Runs after dependencies are injected.**

```java
@Component
public class MyBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("🔹 MyBean initialized using afterPropertiesSet()");
    }
}
```
------------------------------------------------------------------
### @PreDestroy (Cleanup Before Bean is Destroyed)

**Runs before the bean is destroyed.**

```java
@Component
public class MyBean {
    @PreDestroy
    public void cleanup() {
        System.out.println("🛑 Cleaning up MyBean...");
    }
}
```

###  DisposableBean#destroy()

**Runs before the container shuts down.**

```java
@Component
public class MyBean implements DisposableBean {
    @Override
    public void destroy() {
        System.out.println("🛑 MyBean is being destroyed...");
    }
}
```

### init-method and destroy-method in @Bean

Works in Java-based configuration (@Configuration + @Bean).

```java
@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public MyBean myBean() {
        return new MyBean();
    }
}

public class MyBean {
    public void init() {
        System.out.println("🔹 MyBean initialized using init-method");
    }
    public void cleanup() {
        System.out.println("🛑 MyBean cleaned up using destroy-method");
    }
}
```


### SUMMARY

```java
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class MyBean implements ApplicationContextAware, InitializingBean, DisposableBean {

    private static ApplicationContext applicationContext;

    public MyBean() {
        System.out.println("1️⃣ Constructor: Bean instance created");
    }

    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
        System.out.println("2️⃣ ApplicationContextAware: Context set");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("3️⃣ @PostConstruct: Custom initialization logic");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("4️⃣ InitializingBean: afterPropertiesSet() method called");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("5️⃣ @PreDestroy: Cleanup before bean is destroyed");
    }

    @Override
    public void destroy() {
        System.out.println("6️⃣ DisposableBean: destroy() method called");
    }
}
```


**OUTPUT:**

```
1️⃣ Constructor: Bean instance created
2️⃣ ApplicationContextAware: Context set
3️⃣ @PostConstruct: Custom initialization logic
4️⃣ InitializingBean: afterPropertiesSet() method called
...
5️⃣ @PreDestroy: Cleanup before bean is destroyed
6️⃣ DisposableBean: destroy() method called

```