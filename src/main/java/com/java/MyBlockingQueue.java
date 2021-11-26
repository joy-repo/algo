package com.java;

import java.util.LinkedList;
import java.util.List;


public class MyBlockingQueue {

    private List queue = new LinkedList();
    private int limit = 10;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
    }


    public synchronized void enqueue(Object item)
            throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        this.queue.add(item);
        notifyAll();

    }


    public synchronized Object dequeue()
            throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        Object o = this.queue.remove(0);
        notifyAll();
        return o;
    }

}
