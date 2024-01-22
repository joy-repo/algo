

//[18:08] Dinesh Ilangovan
//Each customer can make x requests per y seconds

//
//boolean rateLimit(int customerId)

/// fR

///  3  in 1 sec
// 0----1----2
// Fixed Window -> 1sec
// 0 --- 1
// 1----2

// 1000 per sec
// NumberOf users -> 100
//
// -> 1 JVM -> all the request . and do ratelimiting ofr us

//  Map -> User,NumberOfRequest  --> number RequestTime window has started
//  // 1---2 .. Reset the Map Synchronous Operation
//
//

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleRateLimiter {


  ///Sliding
  // UserID --> lastTime and NumofRq
  /// lastTime, NumofRq, time-1
  /// User ---> time, LastTime-1


  //// User ---> time, Last-1
  /// 1 --->1 ,0
  /// 1 --->1.02 , 1
  /// 1 =---> 1.3 , 2
  /// 1 =---> 1.4 , 3
  /// 1 =---> 1.5 , 4 ---> PririotyQueue -> User,Time ---> 1.5 , 4
  /// 1 =---> 1.5 , 4
  private static final int REQUEST_PER_SEC = 4;
  Map<Integer, Integer> requestsMap= new ConcurrentHashMap<>();


  public boolean rateLimit(int customerId) {
    requestsMap.putIfAbsent(customerId, 0);

    if (requestsMap.get(customerId) >= REQUEST_PER_SEC) {
      System.out.println("Rate limited customerId : " + customerId + "-- Number Of request served " + requestsMap.get(customerId));
      return false;
    }

    requestsMap.put(customerId, requestsMap.get(customerId) + 1);
    System.out.println("Request Success customerId : " + customerId + "-- Number Of request served " + requestsMap.get(customerId));

    return true;

  }

  private void timer() {

    try {
      TimeUnit.SECONDS.sleep(1);
      mapReset();

    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  public void mapReset() {
    synchronized (this) {
      System.out.println("CleanUp Executed");
      requestsMap = new ConcurrentHashMap<>();
    }
  }

  public static void main(String[] args) throws InterruptedException {

    ExecutorService execService = Executors.newFixedThreadPool(1);

    SimpleRateLimiter simpleRateLimiter = new SimpleRateLimiter();

    execService.submit(() -> simpleRateLimiter.timer());

    Random random = new Random();

    for (int i = 0; i < 10; i++) {
      TimeUnit.MILLISECONDS.sleep(random.nextInt(400));
      simpleRateLimiter.rateLimit(2);
    }

  }

}


