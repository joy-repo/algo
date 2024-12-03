package arrays;

// TODO: https://www.youtube.com/watch?v=JDOXKqF60RQ

import java.util.Arrays;

public class FindNextPermutation {

    public static int[] ARR = {2, 4, 1, 7, 5, 0};
    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int i = ARR.length-2;

        while(i>=0 && ARR[i+1]<ARR[i]){
            i--;
        }
        int brkPnt = i;
        for (int j = ARR.length-1; j>brkPnt; j--){
            if(ARR[brkPnt]<ARR[j]){
                int temp = ARR[brkPnt];
                ARR[brkPnt] = ARR[j];
                ARR[j] = temp;
                break;
            }
        }

        int start = brkPnt+1;
        int end = ARR.length-1;

        while (start<end){
            int temp = ARR[start];
            ARR[start]=ARR[end];
            ARR[end] = temp;
            start++;
            end--;
        }

        Arrays.stream(ARR).forEach(System.out::print);
    }
}
