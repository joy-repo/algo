



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

### ScopedProxyMode Types

|Proxy Mode|	Explanation |
|---|---|
|ScopedProxyMode.TARGET_CLASS|	Uses CGLIB proxy (for classes)|
|ScopedProxyMode.INTERFACES	|Uses JDK dynamic proxy (for interfaces)|

👉 **Use TARGET_CLASS for classes and INTERFACES if working with interfaces.**

Example :

```java
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionScopedBean implements MyInterface {
    public String getMessage() {
        return "Session Scoped Bean - " + System.identityHashCode(this);
    }
}
```

**Where is Scoped Proxy Needed?**

* Injecting request/session/application scoped beans into singleton beans.
* Preventing incorrect bean reuse across different users or requests.


|Scope	|Needs Scoped Proxy?|	Why?|
|---|---|---|
|Singleton|	❌ No|	Already global|
|Prototype|	❌ No|	New instance created anyway|
|Request|	✅ Yes|	New bean per request but injected into singleton|
|Session|	✅ Yes|	New bean per user session but injected into singleton|
|Application|	❌ No|	Single instance per application|


### Behind the Scenes: What Happens Internally?

When ScopedProxyMode.TARGET_CLASS is used, 
Spring does NOT inject the actual RequestScopedBean into SingletonBean. 
Instead:

1. Spring creates a subclass proxy (CGLIB proxy for classes).
2. The proxy does not contain the actual bean but knows how to retrieve it from the BeanFactory.
3. Each time requestScopedBean.getMessage() is called:
     * The proxy fetches the correct bean from the Spring context (per request).
     * The method is executed on the actual instance corresponding to the current request.


### How ProxyBean is created

```java
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RequestScopedBean {
    public String getMessage() {
        return "Request Scoped Bean - " + System.identityHashCode(this);
    }
}

```

The above code gets converted to :

```java
// Pseudo-code representing what Spring does internally
public class RequestScopedBean$$EnhancerBySpringCGLIB extends RequestScopedBean {
    private final BeanFactory beanFactory;

    public String getMessage() {
        // Dynamically fetch the real instance from Spring context
        RequestScopedBean realBean = (RequestScopedBean) beanFactory.getBean("requestScopedBean");
        return realBean.getMessage();
    }
}
```
### What Happens in ScopedProxyMode.INTERFACES?
* If the bean is an interface, Spring uses JDK dynamic proxy instead of CGLIB.
* The proxy implements the interface and delegates calls dynamically.

```java
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionScopedBean implements MyInterface {
    public String getMessage() {
        return "Session Scoped Bean - " + System.identityHashCode(this);
    }
}
```

changes to :

```java
public class $Proxy123 implements MyInterface {
    private final BeanFactory beanFactory;

    public String getMessage() {
        MyInterface realBean = (MyInterface) beanFactory.getBean("sessionScopedBean");
        return realBean.getMessage();
    }
}
```

🔹 The proxy fetches the correct instance dynamically per session.