# Reentrant Locks

A ReentrantLock is an alternative to the synchronized keyword, offering advanced locking mechanisms.

### Key Features
* Reentrancy: A thread that holds the lock can reacquire it multiple times.
* Fairness: Can be configured to grant locks in a fair manner (FIFO order).
* Try-Lock: Ability to attempt a lock without blocking.
* Interruptible Locks: Supports interruption while waiting for the lock.

```java
import java.util.concurrent.locks.ReentrantLock;

class SharedResource {
    private final ReentrantLock lock = new ReentrantLock();

    public void safeMethod() {
        lock.lock();  // Acquires the lock
        try {
            System.out.println(Thread.currentThread().getName() + " is executing");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // Ensures release
        }
    }
}

public class ReentrantLockExample {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Runnable task = resource::safeMethod;

        Thread t1 = new Thread(task, "Thread-1");
        Thread t2 = new Thread(task, "Thread-2");

        t1.start();
        t2.start();
    }
}
```

|Method| Description|
|---|---|
|void lock()|Acquires the lock, blocking if necessary.|
|void unlock()|Releases the lock. Must be called in finally.|
|void lockInterruptibly()|Acquires the lock but allows interruption while waiting.|
|boolean tryLock()|Attempts to acquire the lock without blocking, returns true if successful.|
|boolean tryLock(long timeout, TimeUnit unit)|Tries to acquire the lock within a given timeout.|


Key Takeaway: lockInterruptibly() allows a waiting thread to be interrupted, preventing indefinite blocking.


## 3. Lock Query Methods

|Method |Description|
|---|---|
|boolean isLocked()|Returns true if the lock is held by any thread.|
|boolean isHeldByCurrentThread()|Returns true if the current thread holds the lock.|
|boolean hasQueuedThreads()|Returns true if any threads are waiting to acquire the lock.|
|boolean hasQueuedThread(Thread thread)|Checks if a specific thread is waiting for the lock.|
|int getQueueLength()|Returns the number of threads waiting to acquire the lock.|

Example:

```java
import java.util.concurrent.locks.ReentrantLock;

class LockStatus {
    private final ReentrantLock lock = new ReentrantLock();

    public void checkLockStatus() {
        System.out.println("Is Locked? " + lock.isLocked());
        lock.lock();
        try {
            System.out.println("Is Locked After Locking? " + lock.isLocked());
            System.out.println("Is Held By Current Thread? " + lock.isHeldByCurrentThread());
        } finally {
            lock.unlock();
        }
    }
}

public class LockQueryExample {
    public static void main(String[] args) {
        LockStatus status = new LockStatus();
        status.checkLockStatus();
    }
}
```


## 4. Fairness & Queueing Methods

|Method |Description|
|---|---|
|ReentrantLock(boolean fair)|Creates a fair (true) or non-fair (false) lock.|
|boolean isFair()|Returns true if the lock is fair.|

```java
private static final ReentrantLock fairLock = new ReentrantLock(true);  // Fair lock
```

**Key Takeaway: A fair lock grants access in FIFO order, avoiding starvation.**

## 5. Condition Variables (Wait & Signal)

|Method |Description|
|---|---|
|Condition newCondition()|Creates a condition variable for waiting and signaling.|
|void await()|Causes the thread to wait until signaled.|
|void signal()|Wakes up one waiting thread.|
|void signalAll()|Wakes up all waiting threads.|

## Producer-Consumer-Reentrant:

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.LinkedList;

class SharedQueue {
    private final LinkedList<Integer> queue = new LinkedList<>();
    private final int capacity = 5;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                notFull.await();  // Wait if full
            }
            queue.add(value);
            System.out.println("Produced: " + value);
            notEmpty.signal();  // Notify consumer
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();  // Wait if empty
            }
            int value = queue.removeFirst();
            System.out.println("Consumed: " + value);
            notFull.signal();  // Notify producer
            return value;
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionExample {
    public static void main(String[] args) {
        SharedQueue queue = new SharedQueue();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.produce(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.consume();
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Consumer");

        producer.start();
        consumer.start();
    }
}
```









