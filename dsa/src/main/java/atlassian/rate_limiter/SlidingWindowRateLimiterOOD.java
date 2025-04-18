package atlassian.rate_limiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// Step 1: Define a Rate Limiter Interface
interface RateLimiterSW {
    boolean allowRequest(String userId);
}

// Step 2: Encapsulated Sliding Window for a User
class SlidingWindow {
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<Long, AtomicInteger> requestCounts;
    private final int maxRequests;

    public SlidingWindow(long windowSizeInMillis, int maxRequests) {
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestCounts = new ConcurrentHashMap<>();
        this.maxRequests=maxRequests;
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - windowSizeInMillis;

        // Remove expired timestamps
        requestCounts.entrySet().removeIf(entry -> entry.getKey() < windowStart);

        // Compute total requests in the current window
        int totalRequests = requestCounts.values().stream().mapToInt(AtomicInteger::get).sum();

        if (totalRequests < maxRequests) {
            requestCounts.compute(currentTime, (key, value) -> {
                if (value == null) {
                    return new AtomicInteger(1);
                }
                value.incrementAndGet();
                return value;
            });
            return true; // Request allowed
        }
        return false; // Too many requests
    }
}

// Step 3: Encapsulated User-Specific Sliding Window Tracker
//class UserSlidingWindow {
//    private final int maxRequests;
//    private final SlidingWindow slidingWindow;
//
//    public UserSlidingWindow(int maxRequests, long windowSizeInMillis) {
//        this.maxRequests = maxRequests;
//        this.slidingWindow = new SlidingWindow(windowSizeInMillis);
//    }
//
//    public boolean allowRequest() {
//        return slidingWindow.allowRequest(maxRequests);
//    }
//}

// Step 4: Sliding Window Rate Limiter for Multiple Users
public class SlidingWindowRateLimiterOOD implements RateLimiterSW {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, SlidingWindow> userWindows;

    public SlidingWindowRateLimiterOOD(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000L;
        this.userWindows = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        userWindows.computeIfAbsent(userId, key -> new SlidingWindow(windowSizeInMillis, maxRequests));
        return userWindows.get(userId).allowRequest();
    }

    public static void main(String[] args) {
        RateLimiterSW limiter = new SlidingWindowRateLimiterOOD(3, 1);
        String userId1 = "User123";
        String userId2 = "User456";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        for (int i = 0; i < 25; i++) {
            System.out.println("Time: "+ LocalDateTime.now().format(formatter)+ " : User 1 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId1) ? "Allowed" : "Rejected"));
          //  System.out.println("User 2 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId2) ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(100); // Simulate request interval
            } catch (InterruptedException ignored) {}
        }
    }
}