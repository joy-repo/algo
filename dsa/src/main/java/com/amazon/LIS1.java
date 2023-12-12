package com.amazon;

import java.util.Arrays;

public class LIS1 {

    static int[] ARR = {1, 2, 0, 3, 8};

    public static void main(String[] args) {
        solByDP();
       // System.out.println(solByRECC(1));
    }

//    private static int solByRECC(int pos, int val) {
//
//
//        for (int i=0; i<pos;i++){
//            if(ARR[i]<val)
//        }
//
//
//    }

    private static void solByDP() {
        int[] DP = new int[ARR.length];
        Arrays.fill(DP,1);

        for(int i=1; i< ARR.length; i++){
            for(int j=0; j< i; j++){
                if(ARR[i]>ARR[j])
                    DP[i]= Math.max(DP[i], DP[j]+1);
            }
        }
        System.out.println(Arrays.toString(DP));
    }
}
