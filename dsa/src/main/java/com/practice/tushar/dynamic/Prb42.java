package com.practice.tushar.dynamic;

//Number of binary tree with pre-order sequence
//https://www.youtube.com/watch?v=RUB5ZPfKcnY&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=42

import java.util.Arrays;

public class Prb42 {

    static int NUM_ELEMENTS= 6;

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int[] DP = new int[NUM_ELEMENTS+1];

        DP[0]=1;
        DP[1]=1;

        for(int i =2; i<=NUM_ELEMENTS;i++)
            for(int j=0; j<i;j++)
                DP[i]=DP[j]*DP[i-j-1]+DP[i];


        System.out.println(Arrays.toString(DP));
    }
}
