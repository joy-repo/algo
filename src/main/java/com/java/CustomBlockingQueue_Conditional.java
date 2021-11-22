package com.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class CustomBlockingQueue {

    final Lock lock = new ReentrantLock();

    // Conditions
    final Condition produceCond = lock.newCondition();
    final Condition consumeCond = lock.newCondition();

    // Array to store element for CustomBlockingQueue
    final Object[] array = new Object[6];
    int putIndex, takeIndex;
    int count;

    public void put(Object x) throws InterruptedException {

        lock.lock();
        try {
            while (count == array.length) {
                // Queue is full, producers need to wait
                produceCond.await();
            }

            array[putIndex] = x;
            System.out.println("Producing - " + x);
            putIndex++;
            if (putIndex == array.length) {
                putIndex = 0;
            }
            // Increment the count for the array
            ++count;
            consumeCond.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                // Queue is empty, consumers need to wait
                consumeCond.await();
            }
            Object x = array[takeIndex];
            System.out.println("Consuming - " + x);
            takeIndex++;
            if (takeIndex == array.length) {
                takeIndex = 0;
            }
            // reduce the count for the array
            --count;
            // send signal producer
            produceCond.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}


public class CustomBlockingQueue_Conditional {

    public static void main(String[] args) {
        CustomBlockingQueue customBlockingQueue = new CustomBlockingQueue();
        // Creating producer and consumer threads
        Thread producer = new Thread(new Producer(customBlockingQueue));
        Thread consumer = new Thread(new Consumer(customBlockingQueue));

        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {

    private CustomBlockingQueue customBlockingQueue;

    public Producer(CustomBlockingQueue customBlockingQueue) {
        this.customBlockingQueue = customBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                customBlockingQueue.put(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Consumer implements Runnable {
    private CustomBlockingQueue customBlockingQueue;

    public Consumer(CustomBlockingQueue customBlockingQueue) {
        this.customBlockingQueue = customBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                customBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

