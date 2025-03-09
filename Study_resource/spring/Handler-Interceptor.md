## Interceptor


![interceptor.png](interceptor.png)

|Feature |Filter (javax.servlet.Filter)| Interceptor (HandlerInterceptor)|
|---|---|---|
|Runs Before Controller? |Yes (Before DispatcherServlet)|Yes (Before Controller Execution)
|Runs After Controller? |No |Yes (PostHandle & AfterCompletion)
|Can Modify Response? |Yes |Yes
|Runs for Static Resources? |Yes |No



Interceptor is similar to a Servlet Filter, but in contrast 
to the latter, it is located after DispatcherServlet and as a result, 
related HandlerInterceptor class configured inside the 
application context. 

***Filters are known to be more powerful, 
they are allowed to exchange the request and response objects 
handed down the chain.*** </br>

***Interceptors are just allowed to 
add some customer custom pre-processing, the option of prohibiting 
the execution, and also custom post-processing.***


## How to Use Handler Interceptors in Spring?

### 1. Implement HandlerInterceptor Interface

```java
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;

public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre Handle method is called");
        return true;  // Return false to stop request processing
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        System.out.println("Post Handle method is called");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("After Completion method is called");
    }
}
```

### 2. Register Interceptor in Configuration

```java

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/api/**")  // Intercept specific paths
                .excludePathPatterns("/api/public/**"); // Exclude specific paths
    }
}
```

### Interceptor Methods Explained
1.	**preHandle()**
   * Runs before the controller method executes.
   * Returns true to continue the request or false to stop it.
   * Useful for authentication, logging, and modifying request parameters.
2.	**postHandle()**
   * Runs after the controller method executes but before the response is sent.
   * Can modify the ModelAndView if applicable.
   * Useful for adding extra model attributes or logging response time.
3.	**afterCompletion()**
   * Runs after the response is sent to the client.
   * Used for cleanup activities like closing resources or error logging.


**When to Use Interceptors?**
* Logging: Log request and response data.
* Authentication & Authorization: Check if a user is authenticated or authorized.
* Performance Monitoring: Measure time taken by a request.
* Request/Response Modification: Modify request headers, parameters, or response bodies.

--------------------------------------------------------------------------------------
## Example :

**This logic is just a proof of concept – 
we can of course easily achieve the same result using session timeouts – 
but the result is not the point here, the usage of the interceptor is.**

## Handler Interceptor to Manage Sessions

### 1. Custom Implementation of Session Timeouts

For example, if a user forgot to log out, the inactive time counter will prevent accessing the account 
by unauthorized users. 
In order to do that, we need to set constant for the inactive time

```java
private static final long MAX_INACTIVE_SESSION_TIME_MILLIS = 5 * 10000;
```

Now, we need to keep track of each session in our app, so we need to include this Spring Interface:

```java
@Autowired
private HttpSession session;
```

### 2.  ***preHandle()***

In this method we will include following operations:

* setting timers to check handling time of the requests
* checking if a user is logged in 
* automatic logging out, if the user’s inactive session time exceeds maximum allowed value

```java

public class SessionTimerInterceptor implements HandlerInterceptor {

    private static Logger log = LoggerFactory.getLogger(SessionTimerInterceptor.class);

    private static final long MAX_INACTIVE_SESSION_TIME = 5 * 10000;

    @Autowired
    private HttpSession session;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("Pre handle method - check handling start time");
        long startTime = System.currentTimeMillis();
        request.setAttribute("executionTime", startTime);
        if (isUserLogged()) {
            session = request.getSession();
            log.info("Time since last request in this session: {} ms", System.currentTimeMillis() - request.getSession()
                    .getLastAccessedTime());
            if (System.currentTimeMillis() - session.getLastAccessedTime() > MAX_INACTIVE_SESSION_TIME) {
                log.warn("Logging out, due to inactive session");
                SecurityContextHolder.clearContext();
                request.logout();
                response.sendRedirect("/spring-security-rest-full/logout");
            }
        }
        return true;
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView model) throws Exception {
        log.info("Post handle method - check execution time of handling");
        long startTime = (Long) request.getAttribute("executionTime");
        log.info("Execution time for handling the request was: {} ms", System.currentTimeMillis() - startTime);
    }

    public static boolean isUserLogged() {
        try {
            return !SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName()
                    .equals("anonymousUser");
        } catch (Exception e) {
            return false;
        }
    }
}
```

### 3. ***postHandle()***

This method is implementation just to get information, how long it took to process the current request.

```java

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView model) throws Exception {
        log.info("Post handle method - check execution time of handling");
        long startTime = (Long) request.getAttribute("executionTime");
        log.info("Execution time for handling the request was: {} ms", System.currentTimeMillis() - startTime);
    }
```

### 4. Add the Interceptor in the WebMvcConfigurer

```java
@EnableWebMvc
@Configuration
@ComponentScan("com.baeldung.web.controller")
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig() {
        super();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {

        registry.addInterceptor(new SessionTimerInterceptor());
    }

    // API

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        /*
        registry.addViewController("/anonymous.html");
        registry.addViewController("/login.html");
        registry.addViewController("/homepage.html");
         */

    }
   
}
```

