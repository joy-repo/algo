package atlassian.rate_limiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class SlidingWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, ConcurrentHashMap<Long, AtomicInteger>> userRequestCounts = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000L;
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - windowSizeInMillis;

        userRequestCounts.putIfAbsent(userId, new ConcurrentHashMap<>());

        ConcurrentHashMap<Long, AtomicInteger> requestCounts = userRequestCounts.get(userId);

        synchronized (requestCounts) {
            // Remove expired timestamps
            requestCounts.entrySet().removeIf(entry -> entry.getKey() < windowStart);

            // Get total request count in the current window
            int totalRequests = requestCounts.values().stream().mapToInt(AtomicInteger::get).sum();

            if (totalRequests < maxRequests) {
                requestCounts.putIfAbsent(currentTime, new AtomicInteger(0));
                requestCounts.get(currentTime).incrementAndGet();
                return true; // Request allowed
            }
            return false; // Too many requests
        }
    }

    public boolean allowRequestWithout_Synchronized(String userId) {
        long currentTime = System.currentTimeMillis();
        long windowStart = currentTime - windowSizeInMillis;

        userRequestCounts.putIfAbsent(userId, new ConcurrentHashMap<>());
        ConcurrentHashMap<Long, AtomicInteger> requestCounts = userRequestCounts.get(userId);

        // Remove expired timestamps (no need for synchronization here)
        requestCounts.entrySet().removeIf(entry -> entry.getKey() < windowStart);

        // Compute total requests (ConcurrentHashMap ensures thread safety)
        int totalRequests = requestCounts.values().stream().mapToInt(AtomicInteger::get).sum();

        if (totalRequests < maxRequests) {
            // Synchronize only the critical update section
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


    public static void main(String[] args) {


        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(3, 1);
        String userId1 = "User123";
        String userId2 = "User456";

       // LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
       // String formattedDate = now.format(formatter);

        for (int i = 0; i < 20; i++) {
            System.out.println("time: "+ LocalDateTime.now().format(formatter) +" User 1 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId1) ? "Allowed" : "Rejected"));
           // System.out.println("User 2 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId2) ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(100); // Simulate request interval
            } catch (InterruptedException ignored) {}
        }
    }
}