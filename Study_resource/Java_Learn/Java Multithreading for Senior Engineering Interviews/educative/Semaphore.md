

A Semaphore maintains a set of permits. Threads can acquire permits and release permits:

**acquire():** Decreases the permit count by 1. 
If no permits are available, the thread blocks until one becomes available.

**release():** Increases the permit count by 1, 
potentially unblocking a waiting thread.

***Constructor :***

```java
Semaphore(int permits);
Semaphore(int permits, boolean fair);

```

***Methods :*** </br>

| Method |	Description|
|---|---|
|acquire()|	Acquires a permit, blocking if none are available.|
|acquire(int n)|	Acquires n permits, blocking if unavailable.|
|release()|	Releases a permit, increasing the available permits by 1.|
|release(int n)|	Releases n permits.|
|tryAcquire()|	Attempts to acquire a permit without blocking. Returns true if successful, false otherwise.|
|availablePermits()|	Returns the current number of available permits.|
|getQueueLength()|	Returns the number of threads waiting for a permit.|
|hasQueuedThreads()|	Returns true if any threads are waiting for permits.|

## Basic Example :

```java
import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final Semaphore semaphore = new Semaphore(3);  // Allow 3 threads to access concurrently

    public static void main(String[] args) {
        for (int i = 1; i <= 6; i++) {  // Creating 6 threads
            Thread thread = new Thread(new Task(i));
            thread.start();
        }
    }

    static class Task implements Runnable {
        private int threadNumber;

        public Task(int threadNumber) {
            this.threadNumber = threadNumber;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread " + threadNumber + " is waiting for a permit...");
                semaphore.acquire();  // Acquiring a permit

                System.out.println("Thread " + threadNumber + " got a permit!");

                // Simulate some work
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("Thread " + threadNumber + " releases a permit.");
                semaphore.release();  // Releasing the permit
            }
        }
    }
}

```

***OUTPUT :*** </br>

```
Thread 1 is waiting for a permit...
Thread 2 is waiting for a permit...
Thread 3 is waiting for a permit...
Thread 1 got a permit!
Thread 2 got a permit!
Thread 3 got a permit!
Thread 4 is waiting for a permit...
Thread 5 is waiting for a permit...
Thread 6 is waiting for a permit...
Thread 1 releases a permit.
Thread 4 got a permit!
Thread 2 releases a permit.
Thread 5 got a permit!
Thread 3 releases a permit.
Thread 6 got a permit!
Thread 4 releases a permit.
Thread 5 releases a permit.
Thread 6 releases a permit.

```

## Use Cases of Semaphore
 1. Rate Limiting (e.g., allowing only a limited number of clients to access a service).

 2. Pooling Resources (e.g., connection pools, thread pools, etc.).

 3. Database Connections (restricting the number of concurrent connections).

 4. Preventing Overloading (e.g., limiting file uploads or downloads).

