package com.practice.tushar.dynamic;

//Longest Bitonic Subsequence
//https://www.youtube.com/watch?v=TWHytKnOPaQ&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=37

import java.util.Arrays;

public class Prb37 {

    static  int ARR[] = {1, 11, 2, 10, 4, 5, 2, 1};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int[] incr = new int[ARR.length];

        Arrays.fill(incr,1);

        for(int i=0; i< ARR.length;i++){
            for (int j=0; j<i; j++){
                if(ARR[j]<ARR[i])
                    incr[i]=Math.max(incr[i], incr[j]+1);
            }
        }

            int[] decr = new int[ARR.length];

            Arrays.fill(decr,1);

        for(int i=ARR.length-1; i>=0;i--){
            for (int j=ARR.length-1; j>i; j--){
                if(ARR[j]<ARR[i])
                    decr[i]=Math.max(decr[i], decr[j]+1);
            }
        }

        System.out.println(Arrays.toString(incr));
        System.out.println(Arrays.toString(decr));
        System.out.println(Arrays.toString(ARR));

        int res = 0;

        for(int i =0; i< ARR.length; i++){
            res=Math.max(res, incr[i]+decr[i]-1);
        }

        System.out.println(res);

    }


}
