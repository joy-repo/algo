package atlassian.rate_limiter;

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


        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(5, 10);
        String userId1 = "User123";
        String userId2 = "User456";

        for (int i = 0; i < 10; i++) {
            System.out.println("User 1 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId1) ? "Allowed" : "Rejected"));
            System.out.println("User 2 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId2) ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(1000); // Simulate request interval
            } catch (InterruptedException ignored) {}
        }
    }
}