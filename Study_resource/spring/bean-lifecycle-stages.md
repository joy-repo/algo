

## Order

* 1️⃣ Bean Instantiation
* 2️⃣ Dependency Injection (DI)
* 3️⃣ Aware Interfaces Execution (if implemented)
* 4️⃣ BeanPostProcessor - postProcessBeforeInitialization()
* 5️⃣ Initialization (@PostConstruct, InitializingBean#afterPropertiesSet())
* 6️⃣ BeanPostProcessor - postProcessAfterInitialization()
* 7️⃣ Bean Ready for Use ✅
* 8️⃣ Destruction (@PreDestroy, DisposableBean#destroy())


|Stage|Description|
|---|---|
|1. Bean Instantiation |Spring creates an instance of the bean.|
|2. Dependency Injection (DI)|Spring injects dependencies (fields, constructors, setters).
|3. Aware Interfaces Execution| If implemented, Spring calls methods like setBeanName(), setApplicationContext(), etc.|
|4. BeanPostProcessor - Before Initialization|postProcessBeforeInitialization() runs before init methods.|
|5. Initialization |@PostConstruct, InitializingBean#afterPropertiesSet() runs.|
|6. BeanPostProcessor - After Initialization|postProcessAfterInitialization() runs for AOP, proxies, etc.|
|7. Bean Ready for Use|The bean is fully initialized and can be used.|
|8. Destruction|@PreDestroy, DisposableBean#destroy() runs before shutdown.|



### 1. Bean Instantiation

**What Happens?**
* Spring creates an instance of the bean using its constructor or a factory method.
* No dependencies are injected at this point.

**When?**
✔ Happens when Spring scans the component or loads configuration.

### 2. Dependency Injection (DI)

**What Happens?**
* Spring injects dependencies into the bean.
* Dependencies can be injected via:
  * Constructor Injection
  * Setter Injection
  * Field Injection (@Autowired)

**When?** </br>
✔ Happens right after instantiation but before any custom initialization logic.

### 3. Aware Interfaces Execution (if implemented)

**What Happens?**
* 	If the bean implements an Aware interface, Spring calls the respective method.

**When?** </br>
✔ Happens after dependency injection but before initialization methods.

```java
@Component
public class MyBean implements BeanNameAware, ApplicationContextAware {
    @Override
    public void setBeanName(String name) {
        System.out.println("3️⃣ BeanNameAware - Bean Name: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        System.out.println("3️⃣ ApplicationContextAware - ApplicationContext set.");
    }
}
```

### 4. BeanPostProcessor - postProcessBeforeInitialization()

**What Happens?**
* Spring invokes postProcessBeforeInitialization() of all BeanPostProcessor implementations.
* This allows modifying bean properties before initialization.

**When?** </br>
✔ Happens before initialization methods like @PostConstruct or afterPropertiesSet().


```java
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("4️⃣ Before Initialization: " + beanName);
        return bean;
    }
}
```

### 5. Initialization (@PostConstruct, InitializingBean#afterPropertiesSet())

Refer : [Bean-lifecycle.md](Bean-lifecycle.md)

### 6. BeanPostProcessor - postProcessAfterInitialization()

**What Happens?**
* Spring invokes postProcessAfterInitialization() of all BeanPostProcessor implementations.
* This is useful for proxy creation (e.g., AOP, Spring Security, transactions).

**When?** </br>
✔ Happens after initialization methods (@PostConstruct, afterPropertiesSet()).

```java
@Override
public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    System.out.println("6️⃣ After Initialization: " + beanName);
    return bean;
}
```

### 7. Bean Ready for Use 

**What Happens?**
* The bean is fully initialized and ready to be used in the application.
* The application can now start using the bean.

**When?** </br>
✔ Happens after all initialization and post-processing is complete.

### 8. Destruction (@PreDestroy, DisposableBean#destroy())

**What Happens?**
* When the application is shutting down, Spring calls destruction callbacks for cleanup.

**When?** </br>
✔ Happens when the application is shutting down (e.g., context.close() or SIGTERM in Spring Boot).
Refer : [Bean-lifecycle.md](Bean-lifecycle.md)



