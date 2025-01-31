package arrays;

import java.util.HashMap;
import java.util.Map;

/// https://www.youtube.com/watch?v=frf7qxiN2qU

public class Longest_Subarray_with_sum_K {

    static int[] ARR = { 1,2,3,1,1,1,1,4,2,3};
    static int SUM =4;
    public static void main(String[] args) {
        better();
        optimal_ONLY_FOR_POSITIVE_INTRGER();
    }

    private static void better() {
        int sum =0;
        int res =-1;
        int end=-1;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<ARR.length; i++){
            sum = sum+ARR[i];
            end++;
            map.putIfAbsent(sum,i);
            if(sum==SUM){
                res = Math.max(end, res);
            }
            if(sum>SUM){
                if(map.containsKey(sum-SUM))
                    res = Math.max(i-map.get(sum-SUM), res);
            }


        }
        System.out.println(res);

    }

    private static void optimal_ONLY_FOR_POSITIVE_INTRGER() {

        int strt = 0;
        int end = 0;
        int res = 0;
        int sum =0;
        while (!(end == ARR.length-1 && sum < SUM)){

            while(sum<=SUM && end< ARR.length-1){
                end++;
                sum = sum+ARR[end];
                if(sum==SUM) {
                    res = Math.max(end - strt, res);
                    System.out.println("start: "+ strt +" end :"+ end);
                }

            }


            while (sum >= SUM && end>strt){
                strt++;
                sum = sum-ARR[strt];
                if(sum==SUM) {
                    res = Math.max(end - strt, res);
                    System.out.println("start: "+ strt +" end :"+ end);
                }


            }

        }
        System.out.println(res);
    }


}
