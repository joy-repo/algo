package com.practice.tushar.dynamic;

import java.util.Arrays;

//Maximum Sum Increasing Subsequence Dynamic Programming
//https://www.youtube.com/watch?v=99ssGWhLPUE&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=34
public class Prb34 {

    static int[] ARR = {4,6,1,3,8,4,6};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int[] DP = new int[ARR.length];

        for(int i=0; i< ARR.length;i++){
            DP[i]=ARR[i];
        }

        for(int i =0; i< ARR.length; i++){
            for(int j=0; j<i;j++){
                if(ARR[j]<ARR[i]){
                    DP[i]=Math.max(DP[i],ARR[i]+DP[j]);
                }
            }
        }
        System.out.println(Arrays.toString(DP));

    }
}
