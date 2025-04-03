package com.java.concurrency_probs;




public class FixedWaitNotifyExample {
    private static final Object lock = new Object();
    private static boolean isSignaled = false;

    public static void main(String[] args) throws InterruptedException {
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                while (!isSignaled) {  // Check condition in a loop
                    try {
                        System.out.println("Waiter: Waiting for the signal...");
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Waiter: Received the signal!");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Notifier: Sending signal...");
                isSignaled = true;
                lock.notify();
                System.out.println("Notifier: Signal sent.");
            }
        });

        waiter.start();
        Thread.sleep(100);  // Ensure the waiter runs before the notifier
    //    notifier.start();
    }
}

