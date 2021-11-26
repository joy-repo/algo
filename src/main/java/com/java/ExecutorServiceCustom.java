package com.java;

import java.util.concurrent.LinkedBlockingQueue;


interface MyExecutorService {
    void submit(Runnable r);
}

class MyThreadPool implements MyExecutorService {
    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> taskQueue;
    //    static LinkedBlockingQueue<Runner> runnerQueue;
    Execution e;

    public MyThreadPool(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        taskQueue = new LinkedBlockingQueue<Runnable>();
        e = new Execution();
    }

    @Override
    public void submit(Runnable r) {
        if (MyThreadPool.currentCapacity < MyThreadPool.capacity) {
            MyThreadPool.currentCapacity++;
            Thread t = new Thread(new Execution());
            t.start();
        }
        taskQueue.add(r);
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

    @Override
    public void run() {
        while (true) {
            if (MyThreadPool.taskQueue.size() != 0) {
                MyThreadPool.taskQueue.poll().run();
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
        MyExecutorService service = MyExecutors.myNewFixedThreadPool(3);
        for (int i = 0; i < 20; i++) {
            service.submit(new Mytask());
        }
    }
}
