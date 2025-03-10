package com.java.concurrency_probs;

//public class ProducerConsumer_WaitNotify {
//}

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    // Producer method
    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == capacity) {
            System.out.println("Buffer full. Producer waiting...");
            wait(); // Wait if buffer is full
        }
        queue.offer(item);
        System.out.println("Produced: " + item);
        notify(); // Notify the consumer
    }

    // Consumer method
    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Buffer empty. Consumer waiting...");
            wait(); // Wait if buffer is empty
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notify(); // Notify the producer
        return item;
    }
}

// Producer Thread
class Producer implements Runnable {
    private final SharedBuffer buffer;

    public Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int item = 1;
        try {
            while (true) {
                buffer.produce(item++);
                Thread.sleep(500); // Simulating production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer Thread
class Consumer implements Runnable {
    private final SharedBuffer buffer;

    public Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        try {
            while (true) {
                buffer.consume();
                Thread.sleep(1000); // Simulating consumption time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Main Class
public class ProducerConsumer_WaitNotify {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer(5); // Buffer capacity is 5

        Thread producerThread = new Thread(new Producer(buffer));
        Thread consumerThread = new Thread(new Consumer(buffer));

        producerThread.start();
        consumerThread.start();
    }
}
