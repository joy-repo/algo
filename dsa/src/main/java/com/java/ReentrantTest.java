package com.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {

  Lock lock = new ReentrantLock();

  Condition condition = lock.newCondition();

  public static void main(String[] args) throws InterruptedException {

    ReentrantTest ob = new ReentrantTest();
    System.out.println("Print First");
    new Thread(() ->ob.method1()).start();
    new Thread(()-> ob.method2()).start();

   // Thread.sleep(20);





  }

  private void method1() {

    lock.lock();
    try{
      condition.await();
      System.out.println(Thread.currentThread().getName() +" method1 ");
      System.out.println("exec....");
    } catch (InterruptedException  e){
      //exception
    } finally {
      lock.unlock();
     // condition.signalAll();
    }

  }

  private void method2() {

    lock.lock();
    try{
      System.out.println(Thread.currentThread().getName() +" method2 ");
      condition.signalAll();
      System.out.println("exec....");
    } finally {
      lock.unlock();
    }

  }
}
