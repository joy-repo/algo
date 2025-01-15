package test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class MMMM {

    public static void main(String[] args) {

        LocalDateTime localDateTime = LocalDateTime.now();
        long epochSeconds = localDateTime.toEpochSecond(ZoneOffset.UTC);
        System.out.println("Epoch Time in seconds: " + epochSeconds);
// 1730724279
// 1724363078

        LocalDateTime localDateTime1 = new Date().toInstant().atZone(ZoneOffset.UTC).toLocalDateTime();
        System.out.println(localDateTime1);

    }
}
