

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.function.Predicate;

public class TTTTTTT {


    //c3fe7f55-aea5-4083-90e3-f0f38051083e

    // 1288834974657

//    public static void main(String[] args) {
//        int[] org = new int[]{1,2,3};
//        int[] oribbb = Arrays.copyOf(org,5);
//        int[] copy = Arrays.copyOfRange(oribbb, 0,4);
//        for (int i =0; i< copy.length; i++){
//            System.out.println(copy[i] + "");
//        }
//    }

    public static void main(String[] args) {

        System.out.println(UUID.randomUUID());



        String sd = System.getenv("DUMMY");



        assert sd==null;

        System.out.println(sd);

        Predicate p;

//        Trie

        for( int i =0 ; i<=7; i++) {

            int th = 1000 + (-200 + new Random().nextInt(401));

            System.out.println(th);
        }
    }

}
