package atlassian.rate_limiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

// TODO: Step 1: Define an Interface for a Rate Limiter
interface RateLimiter {
    boolean allowRequest(String userId);
}

// TODO: Step 2: Encapsulated Rate Limit Window Class
class RateLimitWindow {
    private final long startTime; // Start time of the window
    private final int maxRequests;
    private final AtomicInteger requestCount;

    public RateLimitWindow(long startTime, int maxRequests) {
        this.startTime = startTime;
        this.maxRequests = maxRequests;
        this.requestCount = new AtomicInteger(0);
    }

    public boolean allowRequest() {
        return requestCount.incrementAndGet() <= maxRequests;
    }

    public long getStartTime() {
        return startTime;
    }
}

// TODO: Step 3: User-Specific Rate Limiter
class UserRateLimiterFW {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private RateLimitWindow currentWindow;

    public UserRateLimiterFW(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.currentWindow = new RateLimitWindow(System.currentTimeMillis(), maxRequests);
    }

    public synchronized boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long fixedWindowStart = (currentTime / windowSizeInMillis) * windowSizeInMillis;

        if (currentWindow.getStartTime() != fixedWindowStart) {
            // Create a new window when the time slot changes
            currentWindow = new RateLimitWindow(fixedWindowStart, maxRequests);
        }
        return currentWindow.allowRequest();
    }
}

// TODO: Step 4: Fixed Window Rate Limiter with User Tracking
public class FixedPredefineRateLimiterWindowInOOD implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, UserRateLimiterFW> userLimiters;

    public FixedPredefineRateLimiterWindowInOOD(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000L;
        this.userLimiters = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        userLimiters.computeIfAbsent(userId, key -> new UserRateLimiterFW(maxRequests, windowSizeInMillis));
        return userLimiters.get(userId).allowRequest();
    }

    public static void main(String[] args) {
        RateLimiter limiter = new FixedPredefineRateLimiterWindowInOOD(3, 1); // 5 requests per 10-second window

        Runnable task = () -> {
            String user = "user1";
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            for (int i = 0; i < 25; i++) {
                System.out.println("Time : " + LocalDateTime.now().format(formatter)  +
                        (limiter.allowRequest(user) ? " :  Allowed" : "  : Rejected"));
                try {
                    Thread.sleep(100); // Simulate request interval
                } catch (InterruptedException ignored) {}
            }
        };

        // Simulating multiple concurrent users
        Thread t1 = new Thread(task);
//        Thread t2 = new Thread(task);
        t1.start();
//        t2.start();
    }
}