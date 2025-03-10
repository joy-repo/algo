package com.java;


import java.time.Instant;
import java.util.concurrent.LinkedBlockingQueue;


interface MyExecutorService {
    void submit(Runnable r);
}

class MyThreadPool implements MyExecutorService {

     int capacity;
     int currentCapacity;
     LinkedBlockingQueue<Runnable> taskQueue;
    //    static LinkedBlockingQueue<Runner> runnerQueue;


    public MyThreadPool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        taskQueue = new LinkedBlockingQueue<Runnable>();

    }

    @Override
    public void submit(Runnable r) {
        if (currentCapacity < capacity) {
            currentCapacity++;
            Thread t = new Thread(new Execution(this));
            t.start();
            return;
        }
        taskQueue.offer(r);
        //e.executeMyMethod();
    }
}

class Execution implements Runnable {
//    void executeMyMethod() {
//        if (MyThreadPool.currentCapacity < MyThreadPool.capacity) {
//            MyThreadPool.currentCapacity++;
//            Thread t = new Thread(new Execution());
//            t.start();
//        }
//    }
    public MyThreadPool myThreadPool;

    public Execution(MyThreadPool myThreadPool){
        this.myThreadPool=myThreadPool;
    }
    @Override
    public void run() {
        while (true) {
            if (myThreadPool.taskQueue.size() != 0) {
                myThreadPool.taskQueue.poll().run();
            }
        }
    }
}

class MyExecutors {
    int capacity;


    static MyExecutorService myNewFixedThreadPool(int capacity) {
        return new MyThreadPool(capacity);
    }
}

class Mytask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hii" + Thread.currentThread().getName());
    }
}

public class ExecutorServiceCustom {
    public static void main(String[] args) {
        MyExecutorService service = MyExecutors.myNewFixedThreadPool(5);
        ExecutorServiceCustom ex = new ExecutorServiceCustom();
        for (int i = 0; i < 20; i++) {
            service.submit(ex::task);
          // OR  service.submit(Mytask::new);
           // OR  service.submit(new Mytask());
        }
    }

    public void task() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hii--" + Thread.currentThread().getName()+ " "+ Instant.now());
    }
}
