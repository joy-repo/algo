package com.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ProducerConsumerLockCondition {

  ReentrantLock lock = new ReentrantLock();
  Condition added = lock.newCondition();
  Condition removed = lock.newCondition();

  public int count = 0;

  public static void main(String[] args) {
    ProducerConsumerLockCondition p = new ProducerConsumerLockCondition();

    ReentrantReadWriteLock l  = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = l.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = l.writeLock();


//    new Thread(()-> p.produce()).start();
//    new Thread(() -> p.consume()).start();
  }


  public void produce() throws InterruptedException {

    try {

      lock.lock();

      while (count == 10)
        removed.await();

      //addData()

      added.signalAll();
    } finally {
      lock.unlock();
    }




  }

  public int consume() throws InterruptedException {

    lock.lock();

    try {

      while (count == 0)
        added.await();

      int res = 2; //addData()

      removed.signalAll();

      lock.unlock();
      return res;
    } finally {
      lock.unlock();
    }


  }
}
