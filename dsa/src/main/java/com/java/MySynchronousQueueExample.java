package com.java;

import java.util.Calendar;


public class MySynchronousQueueExample {

    public static void main(String[] args) throws InterruptedException {
        MySynchronousQueue queue = new MySynchronousQueue();
        Thread th1 = new Thread(() -> {
            while (true) {
                try {
                    queue.put(1);
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //   System.out.println("put:::"+ Calendar.getInstance().getTime());
            }
        });

        Thread th2 = new Thread(() -> {
            while (true) {
                try {
                    queue.take();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //  System.out.println("take:::"+ Calendar.getInstance().getTime());
            }
        });
        th1.start();
        // Thread.sleep(1000);
        th2.start();
    }
}

class MySynchronousQueue {

    public Integer data;
    public volatile boolean isEmpty = true;

    public synchronized void put(Integer i) throws InterruptedException {
        while (!isEmpty) wait();
        data = i;
        isEmpty = false;
        System.out.println("put:::" + Calendar.getInstance().getTime());
        notifyAll();
    }

    public synchronized Integer take() throws InterruptedException {
        while (isEmpty) wait();
        int rData = data;
        isEmpty = true;
        System.out.println("take:::" + Calendar.getInstance().getTime());
        notifyAll();
        return rData;
    }


}