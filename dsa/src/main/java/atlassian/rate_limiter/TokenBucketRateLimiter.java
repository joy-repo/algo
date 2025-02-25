package atlassian.rate_limiter;

import java.util.concurrent.*;

class TokenBucket {
    private int numberOfTokenAvailable;
    private final int numberOfRequests;
    private final int windowSizeForRateLimitInMilliSeconds;
    private long lastRefillTime;
    private long nextRefillTime;
    private final int maxBucketSize;

    public TokenBucket(int maxBucketSize, int numberOfRequests, int windowSizeForRateLimitInMilliSeconds) {
        this.maxBucketSize = maxBucketSize;
        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMilliSeconds = windowSizeForRateLimitInMilliSeconds;
        this.refill();
    }

    public synchronized boolean tryConsume() {
        refill();
        if (this.numberOfTokenAvailable > 0) {
            this.numberOfTokenAvailable--;
            return true;
        }
        return false;
    }

    private synchronized void refill() {
        long currentTime = System.currentTimeMillis();
        if (currentTime < this.nextRefillTime) {
            return;
        }
        this.lastRefillTime = currentTime;
        this.nextRefillTime = this.lastRefillTime + this.windowSizeForRateLimitInMilliSeconds;
        this.numberOfTokenAvailable = Math.min(this.maxBucketSize, this.numberOfTokenAvailable + this.numberOfRequests);
    }
}

class UserRateLimiterTB {
    private final ConcurrentHashMap<String, TokenBucket> userBuckets;
    private final int maxBucketSize;
    private final int numberOfRequests;
    private final int windowSizeForRateLimitInMilliSeconds;

    public UserRateLimiterTB(int maxBucketSize, int numberOfRequests, int windowSizeForRateLimitInMilliSeconds) {
        this.userBuckets = new ConcurrentHashMap<>();
        this.maxBucketSize = maxBucketSize;
        this.numberOfRequests = numberOfRequests;
        this.windowSizeForRateLimitInMilliSeconds = windowSizeForRateLimitInMilliSeconds;
    }

    public boolean tryConsume(String userId) {
        userBuckets.computeIfAbsent(userId, id -> new TokenBucket(maxBucketSize, numberOfRequests, windowSizeForRateLimitInMilliSeconds));
        return userBuckets.get(userId).tryConsume();
    }
}

public class TokenBucketRateLimiter {
    public static void main(String[] args) {
        UserRateLimiterTB rateLimiter = new UserRateLimiterTB(10, 5, 10000); // 10 tokens, refill 5 every 10 sec

        String user1 = "user1";
        String user2 = "user2";

        for (int i = 0; i < 12; i++) {
            System.out.println("User1 request " + (i + 1) + ": " + rateLimiter.tryConsume(user1));
            System.out.println("User2 request " + (i + 1) + ": " + rateLimiter.tryConsume(user2));
        }
    }
}