

* Spurious mean fake or false.
* A spurious wakeup means a thread is woken up even though no signal has been received.
* Spurious wakeups are a reality and are one of the reasons why the pattern for waiting on a
condition variable happens in a while loop as discussed in earlier chapters.

## Why is happens :
* There are technical reasons beyond our current scope as to why spurious wakeups happen,
* but for the curious on POSIX based operating systems when a process is signaled,
* all its waiting threads are woken up.
* Below comment is a directly lifted from Java's documentation for the wait(long timeout) method.



```
* A thread can also wake up without being notified, interrupted, or
* timing out, a so-called <i>spurious wakeup</i>.  While this will rarely
* occur in practice, applications must guard against it by testing for
* the condition that should have caused the thread to be awakened and
* continuing to wait if the condition is not satisfied.  In other words,
* waits should always occur in loops, like this one:
* 
*     synchronized (obj) {
*         while (condition does not hold)
*             obj.wait(timeout);
*         ... // Perform action appropriate to condition
*     }
*
```

