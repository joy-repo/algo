package atlassian.practice;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RateLimiterTest {



    @Test
    public void testRateLimiting(){

        TokenBucketPractice limiter = new TokenBucketPractice(3, 1);
        String userId1 = "User123";
        String userId2 = "User456";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        for (int i = 0; i < 25; i++) {
            System.out.println("Time: "+ LocalDateTime.now().format(formatter)+ " : User 1 - Request " + (i + 1) + ": " + (limiter.rateLimitUser(userId1) ? "Allowed" : "Rejected"));
            //  System.out.println("User 2 - Request " + (i + 1) + ": " + (limiter.allowRequest(userId2) ? "Allowed" : "Rejected"));
            try {
                Thread.sleep(100); // Simulate request interval
            } catch (InterruptedException ignored) {}
        }
    }

}