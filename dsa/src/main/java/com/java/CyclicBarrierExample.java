package com.java;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(4);
        Passenger p1 = new Passenger(barrier, 1000, "joy");
        Passenger p2 = new Passenger(barrier, 1000, "pagla");
        Passenger p3 = new Passenger(barrier, 1000, "indranil");
        Passenger p4 = new Passenger(barrier, 1000, "Somrita");

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(p1);
        executorService.submit(p2);
        executorService.submit(p3);
        executorService.submit(p4);
        executorService.shutdown();

        p1 = new Passenger(barrier, 1000, "joy");
        p2 = new Passenger(barrier, 1000, "pagla");
        p3 = new Passenger(barrier, 1000, "indranil");
        p4 = new Passenger(barrier, 1000, "Somrita");

        executorService = Executors.newFixedThreadPool(5);
        executorService.submit(p1);
        executorService.submit(p2);
        executorService.submit(p3);
        executorService.submit(p4);
        executorService.shutdown();
    }


}


class Passenger implements Runnable {

    CyclicBarrier barrier;
    int delay;
    String name;

    public Passenger(CyclicBarrier barrier, int delay, String name) {
        this.barrier = barrier;
        this.delay = delay;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(delay);
            System.out.println(name + " is arrived.");

            int await = barrier.await();
            System.out.println(name + " ::await:<" + await + ">");
            //if (await == 0) {

                System.out.println(name + " :: Four passengers have arrived so cab is going to start.." + await);
            //}
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
