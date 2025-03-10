package com.java.concurrency_probs;

//public class CustomexecutorService2 {
//}




import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;

interface MyExecutorService {
    void submit(Runnable r);
}

class MyThreadPool implements MyExecutorService {
    private final int capacity;
    public final LinkedBlockingQueue<Runnable> taskQueue;

    public MyThreadPool(int capacity) {
        this.capacity = capacity;
        this.taskQueue = new LinkedBlockingQueue<>();

        // Pre-create worker threads
        for (int i = 0; i < capacity; i++) {
            new Thread(new Execution(this)).start();
        }
    }

    @Override
    public void submit(Runnable r) {
        taskQueue.offer(r);
    }
}

class Execution implements Runnable {
    private final MyThreadPool myThreadPool;

    public Execution(MyThreadPool myThreadPool) {
        this.myThreadPool = myThreadPool;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Blocking call (waits until a task is available)
                Runnable task = myThreadPool.taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Allow thread to exit gracefully
            }
        }
    }
}

class MyExecutors {
    static MyExecutorService newFixedThreadPool(int capacity) {
        return new MyThreadPool(capacity);
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Hello from " + Thread.currentThread().getName() + " " + Instant.now());
    }
}

public class CustomexecutorService2 {
    public static void main(String[] args) {
        MyExecutorService service = MyExecutors.newFixedThreadPool(8);
      //  ExecutorServiceCustom ex = new ExecutorServiceCustom();

        for (int i = 0; i < 20; i++) {
            service.submit(new MyTask()); // Preferred
            // OR service.submit(() -> ex.task());
        }
    }


}

