package com.java;

import java.util.concurrent.*;


//TODO: ExecutorService Internals ---> https://deepakvadgama.com/blog/java-executor-internals/

public class ExecutorService_Internals_Samlpls {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = Executors.newCachedThreadPool();
        // ExecutorService service = Executors.newSingleThreadScheduledExecutor();
        try {

            System.out.println("begin");
            service.execute(() -> System.out.println("Printing zoo inventory"));
            service.execute(() -> {
                for (int i = 0; i < 3; i++)
                    System.out.println("Printing record: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Printing record: DONE");
            });
            System.out.println("1-IsTerminated:<<" + service.isTerminated() + ">>");
            System.out.println("1-IsShutDown:<<" + service.isShutdown() + ">>");
            service.execute(() -> {
                for (int i = 3; i < 6; i++)
                    System.out.println("Printing record: " + i);
            });
            service.execute(() -> System.out.println("Printing zoo inventory"));
            System.out.println("end");
        } finally {
            if (service != null) service.shutdown();
            System.out.println("2-IsTerminated:<<" + service.isTerminated() + ">>");
            System.out.println("2-IsShutDown:<<" + service.isShutdown() + ">>");
        }
        Thread.sleep(2000);
        System.out.println("3-IsTerminated:<<" + service.isTerminated() + ">>");
        System.out.println("3-IsShutDown:<<" + service.isShutdown() + ">>");

        ///////// Scheduled threadpool executor ////////////////

        System.out.println("///////// Scheduled threadpool executor ////////////////");

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        Runnable task1 = () -> System.out.println("Hello Zoo");
        Callable<String> task2 = () -> "Monkey";

        Future<?> result1 = scheduledExecutorService.schedule(task1, 1, TimeUnit.SECONDS);
        Future<String> result2 = scheduledExecutorService.schedule(task2, 4, TimeUnit.SECONDS);


        System.out.println(result2.get());
        scheduledExecutorService.shutdown();

        ///////// Scheduled threadpool executor ScheduleatFixedRate ////////////////

        System.out.println("///////// Scheduled threadpool executor--- ScheduleAtFixedRate ////////////////");

        ScheduledExecutorService scheduledThreadPoolExecutor_rate_delay = Executors.newScheduledThreadPool(1);
//        ScheduledThreadPoolExecutor threadPool
//                = new ScheduledThreadPoolExecutor(2);
        Runnable task3 = () -> System.out.println("Hello Zoo--FixedRate");
        Runnable task4 = () -> System.out.println("Hello Zoo--FixedDelay");
        scheduledThreadPoolExecutor_rate_delay.scheduleAtFixedRate(task3, 1, 2, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor_rate_delay.scheduleAtFixedRate(task4, 1, 2, TimeUnit.SECONDS);
        //scheduledThreadPoolExecutor_rate_delay.shutdown();


    }
}
