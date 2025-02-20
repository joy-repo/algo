package atlassian.rate_limiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedPredefinedWindowRateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Window> userRequestCounts;

    public FixedPredefinedWindowRateLimiter(int maxRequests, int windowSizeInSeconds) {
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
        long fixedWindowStart = (currentTime / windowSizeInMillis) * windowSizeInMillis; // Round to nearest window

        userRequestCounts.compute(userId, (key, window) -> {
            if (window == null || window.windowStart != fixedWindowStart) {
                // New fixed window slot starts
                return new Window(fixedWindowStart);
            }
            return window;
        });

        Window userWindow = userRequestCounts.get(userId);

        // Allow request if it's within the max limit
        if (userWindow.count.incrementAndGet() <= maxRequests) {
            return true;
        }
        return false; // Request limit exceeded
    }

    public static void main(String[] args) {
        FixedPredefinedWindowRateLimiter limiter = new FixedPredefinedWindowRateLimiter(5, 60); // 5 requests per 60 sec window

        Runnable task = () -> {
            String user = "user1";
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " +
                        (limiter.allowRequest(user) ? "Allowed" : "Rejected"));
                try {
                    Thread.sleep(10000); // Simulate request interval
                } catch (InterruptedException ignored) {}
            }
        };

        // Simulating multiple users
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
    }
}