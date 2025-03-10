
## Atomic Operations in Java:

1. **Assignments and reads for primitive data types** except for double and long are always atomic.
2. The reads and writes to **double and long primitive** types **are NOT** atomic.
3. ***In order to make reads and writes to double or long primitive types atomic, we must mark them as volatile. The specification guarantees writes and reads to volatile double and long primitive types as atomic.***
4. All reference assignments are atomic. By reference we mean a variable holding a memory location address, where an object has been allocated by the JVM. For instance, consider the snippet Thread currentThread = Thread.currentThread(); The variable currentThread holds the address for the current thread’s object.

## Synchronized

1. For static methods, the monitor will be the class object, which is distinct from the monitor of each instance of the same class.

2. If an uncaught exception occurs in a synchronized method, the monitor is still released.

3. Furthermore, synchronized blocks can be re-entered.


**1 common Mistake :**

* A classic newbie mistake is to synchronize on an object and then somewhere in the code reassign the object. 
* As an example, look at the code below. We synchronize on a Boolean object in the first thread but sleep before we call wait() on the object. 
* While the first thread is asleep, the second thread goes on to change the flag's value. 
* When the first thread wakes up and attempts to invoke wait(), it is met with a IllegalMonitorState exception! 

```java
class Demonstration {
    public static void main( String args[] ) throws InterruptedException {
        IncorrectSynchronization.runExample();
    }
}

class IncorrectSynchronization {

    Boolean flag = new Boolean(true);

    public void example() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                synchronized (flag) {
                    try {
                        while (flag) {
                            System.out.println("First thread about to sleep");
                            Thread.sleep(5000);
                            System.out.println("Woke up and about to invoke wait()");
                            flag.wait();
                        }
                    } catch (InterruptedException ie) {

                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                flag = false;
                System.out.println("Boolean assignment done.");
            }
        });

        t1.start();
        Thread.sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }

    public static void runExample() throws InterruptedException {
        IncorrectSynchronization incorrectSynchronization = new IncorrectSynchronization();
        incorrectSynchronization.example();
    }
}
```

## Wait & Notify

### wait()

The wait method is exposed on each java object. Each Java object can act as a condition variable. When a thread executes the wait method, it releases the monitor for the object and is placed in the wait queue. 

### notify()

Like the wait method, notify() can only be called by the thread which owns the monitor for the object on which notify() is being called else an illegal monitor exception is thrown. 

### notifyAll()

This method is the same as the notify() one except that it wakes up all the threads that are waiting on the object's monitor.