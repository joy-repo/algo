package blink75;

import javax.xml.stream.events.Characters;

public class PROB {

  public static void main(String[] args) {

    //System.out.println(Character.isLowerCase('A'));
    System.out.println(Character.toLowerCase('9'));
//
//    PROB p = new PROB();
//    new Thread(() -> {
//      try {
//        p.m1();
//      } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//      }
//    }).start();
//    PROB p1 = new PROB();
//    new Thread(() -> {
//      try {
//        p1.m1();
//      } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//      }
//    }).start();
  }

  public synchronized void m1() throws InterruptedException {

    Thread.sleep(5);
  }


}
