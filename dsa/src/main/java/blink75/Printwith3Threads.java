package blink75;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Printwith3Threads {

  volatile int N=10;
  volatile int  counter=1;

  public volatile boolean print1= false;
  public volatile boolean print2= false;
  public volatile boolean print3= false;



  public static void main(String[] args) {
    Printwith3Threads p = new Printwith3Threads();
    p.print1=true;



    new Thread(()-> {
      try {
        p.print1();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();
    new Thread(()-> {
      try {
        p.print2();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();
    new Thread(()-> {
      try {
        p.print3();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }).start();


  }




  public synchronized void print1() throws InterruptedException {


    while (counter <= N) {

      while (!print1) {
        wait();
      }
      if(counter<=N)
      System.out.println(Thread.currentThread().getName() + ":" + counter);
      counter++;
      print1 = false;
      print2 = true;
      print3 = false;
      notifyAll();
    }

  }

  public synchronized void print2() throws InterruptedException {

    while (counter <= N) {

      while (!print2) {
        wait();
      }
      if(counter<=N)
      System.out.println(Thread.currentThread().getName() + ":" + counter);
      counter++;
      print1 = false;
      print2 = false;
      print3 = true;
      notifyAll();
    }

  }

  public synchronized void print3() throws InterruptedException {

    while (counter <= N) {

      while (!print3) {
        wait();
      }

      if(counter<=N)
      System.out.println(Thread.currentThread().getName() + ":" + counter);
      counter++;
      print1 = true;
      print2 = false;
      print3 = false;
      notifyAll();
    }

  }
}
