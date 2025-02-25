package atlassian.rate_limiter;


import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.*;

public class TokenBucked_threadOptimised {
    private final AtomicInteger tokens;
    private final int maxBucketSize;
    private final int numberOfRequestsPerWindow;
    private final int windowSizeForRateLimitInMilliSeconds;
    private volatile long lastRefillTime;
    private final Lock lock = new ReentrantLock(); // Used only for refill to minimize contention

    public TokenBucked_threadOptimised(int maxBucketSize, int numberOfRequests, int windowSize) {
        this.maxBucketSize = maxBucketSize;
        this.numberOfRequestsPerWindow = numberOfRequests;
        this.windowSizeForRateLimitInMilliSeconds = windowSize;
        this.tokens = new AtomicInteger(maxBucketSize);
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean tryConsume() {
        refill(); // Refill outside lock to reduce contention

        // Atomically check and decrement token count
        while (true) {
            int availableTokens = tokens.get();
            if (availableTokens <= 0) return false; // No tokens left

            if (tokens.compareAndSet(availableTokens, availableTokens - 1)) {
                return true; // Successfully consumed a token
            }
        }
    }

    private void refill() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRefillTime < windowSizeForRateLimitInMilliSeconds) {
            return; // Not time to refill yet
        }

        if (lock.tryLock()) { // Avoid unnecessary locking and contention
            try {
                long elapsedTime = System.currentTimeMillis() - lastRefillTime;
                if (elapsedTime >= windowSizeForRateLimitInMilliSeconds) {
                    tokens.set(Math.min(maxBucketSize, tokens.get() + numberOfRequestsPerWindow));
                    lastRefillTime = System.currentTimeMillis();
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        UserRateLimiter rateLimiter = new UserRateLimiter(10, 5, 10000); // 10 tokens, refill 5 every 10 sec

        String user1 = "user1";
        String user2 = "user2";

        for (int i = 0; i < 12; i++) {
            System.out.println("User1 request " + (i + 1) + ": " + rateLimiter.tryConsume(user1));
            System.out.println("User2 request " + (i + 1) + ": " + rateLimiter.tryConsume(user2));
        }
    }
}

class UserRateLimiter {
    private final ConcurrentHashMap<String, TokenBucked_threadOptimised> userBuckets;
    private final int maxBucketSize;
    private final int numberOfRequestsPerWindow;
    private final int windowSizeForRateLimitInMilliSeconds;

    public UserRateLimiter(int maxBucketSize, int numberOfRequests, int windowSize) {
        this.userBuckets = new ConcurrentHashMap<>();
        this.maxBucketSize = maxBucketSize;
        this.numberOfRequestsPerWindow = numberOfRequests;
        this.windowSizeForRateLimitInMilliSeconds = windowSize;
    }

    public boolean tryConsume(String userId) {
        return userBuckets
                .computeIfAbsent(userId, id -> new TokenBucked_threadOptimised(maxBucketSize, numberOfRequestsPerWindow, windowSizeForRateLimitInMilliSeconds))
                .tryConsume();
    }
}

