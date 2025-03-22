package atlassian.practice;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;




public class TokenBucketPractice {

    private int maxBucketSize ;
    private int windowSizeInSeconds;
    ScheduledExecutorService scheduler;


    private ConcurrentHashMap<String, Integer> userTokensMap ;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public TokenBucketPractice(int maxBucketSize, int windowSizeInSeconds){
        this.maxBucketSize=maxBucketSize;
        this.windowSizeInSeconds =windowSizeInSeconds;
        this.userTokensMap = new ConcurrentHashMap<>();
        scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(this::refillTokens, 0,this.windowSizeInSeconds, TimeUnit.SECONDS );

    }

    public  boolean rateLimitUser(String userId){
        userTokensMap.putIfAbsent(userId, maxBucketSize);
        return userTokensMap.compute(userId, (u, t)->  t-1) >=0;

    }

    private  void refillTokens() {
       // userTokensMap.merge(userId, maxBucketSize, (oldTokens, max) -> Math.max(oldTokens, max))

        //System.out.println("refillTokens - Time: "+ LocalDateTime.now().format(formatter));
        for(String userId : userTokensMap.keySet()){
            userTokensMap.merge(userId, maxBucketSize, (oldTokens, max) -> maxBucketSize);
            //userTokensMap.put(userId, maxBucketSize);
        }
    }

    public void shutdown(){
        scheduler.shutdown();
    }
}

