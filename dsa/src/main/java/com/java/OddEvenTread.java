package com.java;

public class OddEvenTread {


  // Starting counter
  int counter = 1;

  static int N;

  // Function to print odd numbers
  public synchronized void printOddNumber() {

      // Print number till the N
      while (counter < N) {
  // If count is even then print
        while (counter % 2 == 0) {
 // Exception handle
          try {
            wait();
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
     // Print the number
        System.out.println(Thread.currentThread().getName() + "--" +counter + " ");
   // Increment counter
        counter++;
    // Notify to second thread
        notify();
      }

  }

  // Function to print even numbers
  public synchronized void printEvenNumber() {

      // Print number till the N
      while (counter < N) {

        // If count is odd then print
        while (counter % 2 == 1) {

          // Exception handle
          try {
            wait();
          }
          catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
       // Print the number
        System.out.println(Thread.currentThread().getName() + "--" +counter + " ");
     // Increment counter
        counter++;
       // Notify to 2nd thread
        notify();
      }

  }

  // Driver Code
  public static void main(String[] args)
  {
    // Given Number N
    N = 10;

    // Create an object of class
    OddEvenTread mt = new OddEvenTread();

    // Create thread t1
    Thread t1 = new Thread(()->mt.printOddNumber());


    // Create thread t2
    Thread t2 = new Thread(()->mt.printEvenNumber());

    // Start both threads
    t1.start();
    t2.start();
  }
}

