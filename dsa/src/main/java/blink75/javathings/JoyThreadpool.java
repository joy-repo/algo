package blink75.javathings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class JoyThreadpool {

 // Executors exc = Executors.newFixedThreadPool()

   List<MyThread> threadPool = new ArrayList<>();
  private LinkedBlockingQueue<Task> taskList = new LinkedBlockingQueue<>();
  private LinkedBlockingQueue<Task> completedTaskList = new LinkedBlockingQueue<>();

  private AtomicInteger totalTask= new AtomicInteger(0);

  int numberOfThreads;

  public void submit(Task task){
    taskList.offer(task);
    totalTask.getAndIncrement();
  }


  public JoyThreadpool(int capacity){
    this.numberOfThreads=capacity;
    for(int i=1 ; i<=capacity; i++){
      MyThread thd = new MyThread();
      thd.start();
      threadPool.add(thd);
    }
  }

  interface Task {

    public void execute();
  }

  class MyThread extends Thread {

    public void run() {
      //    ResultListener<V> result = pool.getResultListener();
      //    Callable<V> task;
      try {

        while (true) {
          Task task = null;

            task = taskList.poll(2, TimeUnit.MILLISECONDS);

          if (task != null) {
            try {
              task.execute();
              completedTaskList.offer(task);

            } catch (Exception e) {
              System.out.println("Task execution error");
            }
          }
        }
      } catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }



  }

  public static void main(String[] args) {

    JoyThreadpool pool = new JoyThreadpool(2);

    Task task1 = () -> {

      try {
        Thread.sleep(4);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println(Thread.currentThread() + " Print 1");
    };

    Task task2 = () -> {

      try {
        Thread.sleep(4);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println(Thread.currentThread() +"Print 2");
    };

    Task task3 = () -> {

      try {
        Thread.sleep(4);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }

      System.out.println(Thread.currentThread() + "Print 3");
    };



    pool.submit(task1);
    pool.submit(task2);
    pool.submit(task3);
    pool.submit(task3);
    pool.submit(task3);
    pool.submit(task3);
    pool.submit(task3);
    pool.shutdown();
  }

  public void shutdown() {

    while (true) {

      if (totalTask.get() == completedTaskList.size()) {
        System.out.println("All Task Completed");
        System.out.println("totalTask.get() ;"+ totalTask.get());
        System.out.println("completedTaskList.size() ;"+ completedTaskList.size());

        threadPool.forEach(th -> {
          System.out.println("interrupt " + th.getName());
          th.interrupt();

        });
        break;
      }
    }

  }
}
