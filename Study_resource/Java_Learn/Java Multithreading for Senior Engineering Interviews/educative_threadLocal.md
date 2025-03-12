## What is ThreadLocal?

ThreadLocal<T> is a Java class that provides thread-local variables.</br> 
These variables are different for each thread, meaning each thread has its own isolated copy of the variable, which is not shared among other threads.


### Use Case

It is useful when you need per-thread state, such as:
* User session management
* Thread-safe object handling (e.g., SimpleDateFormat, database connections)
* Performance optimizations by avoiding synchronization

**Each thread accessing a ThreadLocal variable gets a separate copy of the variable, stored in an internal ThreadLocalMap inside the thread itself.**

### ThreadLocal API Methods
1.	set(T value): Assigns a value to the thread-local variable.
2.	get(): Retrieves the current thread’s value.
3.	remove(): Removes the value for the current thread (prevents memory leaks).
4.	initialValue(): Can be overridden to provide an initial value.

**Basic ThreadLocal syntax**

```java
// Creating a ThreadLocal variable
private static final ThreadLocal<T> threadLocalVar = new ThreadLocal<>();

// Setting a value in the ThreadLocal variable
threadLocalVar.set(value);

// Getting the value from the ThreadLocal variable
T value = threadLocalVar.get();

// Removing the value (to prevent memory leaks)
threadLocalVar.remove();
```

**Using ThreadLocal with withInitial():**

```java
private static final ThreadLocal<String> userThreadLocal = ThreadLocal.withInitial(() -> "Guest");
```

```java
class UserContext {
    private static final ThreadLocal<String> userThreadLocal = ThreadLocal.withInitial(() -> "Guest");

    public static void setUser(String user) {
        userThreadLocal.set(user);
    }

    public static String getUser() {
        return userThreadLocal.get();
    }

    public static void clear() {
        userThreadLocal.remove();
    }
}

public class ThreadLocalExample {
    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            UserContext.setUser("User-" + threadName);
            System.out.println(threadName + " -> " + UserContext.getUser());
            UserContext.clear(); // Cleanup
        };

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        t1.start();
        t2.start();
    }
}
```