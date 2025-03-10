package com.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//
// Condition factors out the Object monitor methods (wait, notify and notifyAll) into distinct objects to give
// the effect of having multiple wait-sets per object, by combining them with the use of arbitrary
// Lock implementations.
//
// Where a Lock replaces the use of synchronized methods and statements, a Condition replaces
// the use of the Object monitor methods.
//
// Conditions (also known as condition queues or condition variables) provide a means for one thread to
// suspend execution (to "wait")
// until notified by another thread that some state condition may now be true. Because access to this
// shared state information
// occurs in different threads, it must be protected, so a lock of some form is associated with the condition.
// The key property that waiting for a condition provides is that it atomically releases the associated lock
// and suspends
// the current thread, just like Object.wait.
//
// A Condition instance is intrinsically bound to a lock. To obtain a Condition instance for a particular Lock instance use
// its newCondition() method.
//
// As an example, suppose we have a bounded buffer which supports put and take methods.
// If a take is attempted on an empty buffer, then the thread will block until an item becomes available;
// if a put is attempted on a full buffer, then the thread will block until a space becomes available.
// We would like to keep waiting put threads and take threads in separate wait-sets so that
// we can use the optimization of only notifying a single thread at a time when items or spaces become available in the buffer.
// This can be achieved using two Condition instances.

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
        Thread producer = new Thread(new com.java.concurrency_probs.Producer(customBlockingQueue));
        Thread consumer = new Thread(new com.java.concurrency_probs.Consumer(customBlockingQueue));

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

