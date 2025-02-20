package atlassian.rate_limiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Window> userRequestCounts;

    public FixedWindowRateLimiter(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000L;
        this.userRequestCounts = new ConcurrentHashMap<>();
    }

    private static class Window {
        long windowStart;
        AtomicInteger count;

        Window(long start) {
            this.windowStart = start;
            this.count = new AtomicInteger(0);
        }
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userRequestCounts.compute(userId, (key, window) -> {
            if (window == null || currentTime - window.windowStart >= windowSizeInMillis) {
                // Start new window
                return new Window(currentTime);
            }
            return window;
        });

        Window userWindow = userRequestCounts.get(userId);

        // If requests are within the limit, allow the request
        if (userWindow.count.incrementAndGet() <= maxRequests) {
            return true;
        }
        return false; // Request limit exceeded
    }

    public static void main(String[] args) {
        FixedPredefineRateLimiterWindowInOOD limiter = new FixedPredefineRateLimiterWindowInOOD(5, 10); // 5 requests per 10 seconds

        Runnable task = () -> {
            String user = "user1";
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " +
                        (limiter.allowRequest(user) ? "Allowed" : "Rejected"));
                try {
                    Thread.sleep(2000); // Simulate request interval
                } catch (InterruptedException ignored) {}
            }
        };

        // Simulating multiple concurrent users
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}
