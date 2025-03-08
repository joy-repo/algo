



| Scope |	Instance Creation | 	Scope of Usage        |	Use Case |
|---|---|------------------------|---|
|Singleton (Default) |	One instance per container| 	Entire application    |	Shared services, database connections|
|Prototype|	New instance every time	| Method call            |	User-specific operations |
|Request (Web)|	One per HTTP request| 	Web request lifecycle |	Tracking request-based data|
|Session (Web)|	One per user session| 	Web session lifecycle |	Managing user session data|
|Application (Web)|	One per application	| Servlet context        |	Application-wide settings
|WebSocket (Web)|	One per WebSocket session| 	WebSocket session	    |Real-time messaging

```java
@Bean
@Scope("singleton")
public MyBean myBean() {
    return new MyBean();
}
```

or **by default**

```java
@Component
public class SingletonBean {
}
```


## What is Scoped Proxy?

In Spring, when you use scopes like request or session, injecting these beans into a singleton bean does not work as expected.
* A singleton bean is created once and reused, but a request or session scoped bean is created per request/session.
* If you inject a request scoped bean into a singleton, Spring injects the same instance for all requests, which is incorrect.

**Solution?** </br>
Use Scoped Proxy (@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)) 
to create a proxy object instead of injecting the actual bean.

### Problem Scenario

**Scenario: Injecting a request scoped bean into a singleton**

```java
@Component
@Scope("request")  // A new instance should be created per request
public class RequestScopedBean {
    public String getMessage() {
        return "Request Scoped Bean - " + System.identityHashCode(this);
    }
}

@Service
public class SingletonBean {
    private final RequestScopedBean requestScopedBean;

    @Autowired
    public SingletonBean(RequestScopedBean requestScopedBean) {
        this.requestScopedBean = requestScopedBean;
    }

    public String getRequestScopedMessage() {
        return requestScopedBean.getMessage();
    }
}
```

🚨 **Problem:**
* Since SingletonBean is singleton, requestScopedBean is injected only once during application startup.
* Every request will get the same instance, defeating the purpose of request scope.

### Solution: Use ScopedProxyMode.TARGET_CLASS

By adding proxyMode = ScopedProxyMode.TARGET_CLASS, Spring injects a proxy instead of a direct instance.

```java

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //TODO: here
public class RequestScopedBean {
    public String getMessage() {
        return "Request Scoped Bean - " + System.identityHashCode(this);
    }
}

@Service
public class SingletonBean {
    private final RequestScopedBean requestScopedBean;

    @Autowired
    public SingletonBean(RequestScopedBean requestScopedBean) {
        this.requestScopedBean = requestScopedBean;
    }

    public String getRequestScopedMessage() {
        return requestScopedBean.getMessage();
    }
}
```

