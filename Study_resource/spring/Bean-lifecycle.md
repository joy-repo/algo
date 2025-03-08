

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

z
