

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

        System.out.println(getSignature("hello"));
    }

    private static String getSignature(String s) {
        int[] freq = new int[26]; // Only lowercase letters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return Arrays.toString(freq); // Convert frequency array to a unique key
    }

}
