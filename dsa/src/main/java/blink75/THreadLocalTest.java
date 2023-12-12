package blink75;

public class THreadLocalTest {

  public static void main(String[] args) {
    ThreadLocal<Integer> thd = new ThreadLocal<>();
    thd.set(2);

  }
}
