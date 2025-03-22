package atlassian.practice;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Data
@AllArgsConstructor
public class TokenBucketPractice {

    private int maxBucketSize ;
    private int windowSizeInMilli;
    ScheduledExecutorService scheduler;


    private ConcurrentHashMap<String, Integer> userTokensMap ;

    public TokenBucketPractice(int maxBucketSize, int windowSizeInMilli){
        this.maxBucketSize=maxBucketSize;
        this.windowSizeInMilli=windowSizeInMilli;
        this.userTokensMap = new ConcurrentHashMap<>();
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(this::refillTokens, 0,windowSizeInMilli, TimeUnit.MILLISECONDS );

    }

    public  boolean rateLimitUser(String userId){
        userTokensMap.putIfAbsent(userId, maxBucketSize);
        return userTokensMap.compute(userId, (u, t)-> (t>0) ? t-1 : t) >0;

    }

    private  void refillTokens() {
       // userTokensMap.merge(userId, maxBucketSize, (oldTokens, max) -> Math.max(oldTokens, max))

        for(String userId : userTokensMap.keySet()){
            userTokensMap.merge(userId, maxBucketSize, (oldTokens, max) -> maxBucketSize);
            //userTokensMap.put(userId, maxBucketSize);
        }
    }

    public void shutdown(){
        scheduler.shutdown();
    }
}

