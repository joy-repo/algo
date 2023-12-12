package com.java;


//-TODO:-------http://www.javabyexamples.com/handling-exceptions-from-executorservice-tasks----
// -TODO:-- github--- https://github.com/isaolmez/javabyexamples/tree/master/java-and-concurrency/advanced/src/main/java/com/javabyexamples/java/concurrency/cancellation/exceptionhandling

//If a thread terminates due to an uncaught exception, the JVM notifies the
// thread's registered UncaughtExceptionHandler.
// If there is no registered handler, it prints the stack trace to System.err.

//Keep in mind that Executors.newFixedThread uses the DefaultThreadFactory class to create the worker threads.
//
// And DefaultThreadFactory doesn't assign an UncaughtExceptionHandler to new threads.
// After we initialize the thread pool, we're executing a Runnable task that throws a RuntimeException.
// the JVM prints the exception stack trace to the console, since the worker thread doesn't have a registered UncaughtExceptionHandler.

///-----------------Exec

public class ThreadExceptionandler extends Thread {

    int count = 0;

    public static void main(String[] args) {
        ThreadExceptionandler t1 = new ThreadExceptionandler();
        t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.printf("Exception thrown by %s with id : %d",
                        t.getName(), t.getId());
                System.out.println("\n" + e.getClass());
            }
        });
        t1.start();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("FirstThread doing something urgent, count : "
                    + (count++));
            throw new RuntimeException();
        }

    }
}
