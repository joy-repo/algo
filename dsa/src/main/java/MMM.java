import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class MMM {



    public static void main(String[] args) {

       // System.out.println(1*2+3^4/5);
        Map<Integer, Integer> m = new HashMap<>();
        int intArr[] = {5,6,7, 10,  15, 20,22, 35 };

        System.out.println(Arrays.binarySearch(intArr, 30));

        int i = Arrays.binarySearch(intArr, 30);

        System.out.println((-1)*(i+2));
        System.out.println(intArr.length);

    }


}
