package com.practice.tushar.dynamic;

//https://www.youtube.com/watch?v=PwDqpOMwg6U&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=40

import java.util.Arrays;

public class Prb40 {

    static int[][] ARR= {
            {2,0,-3,4},
            {6,3,2,-1},
            {5,4,7,3},
            {2,-6,8,1}
    };

    static int [][] DP = new int [ARR.length+1][ARR[0].length+1];

    public static void main(String[] args) {

        sol();
        //int res = query()

    }

    private static void sol() {

        for(int i =1; i<= ARR.length; i++){
            for (int j=1; j<=ARR[0].length; j++){
                DP[i][j]=ARR[i-1][j-1]+DP[i-1][j]+DP[i][j-1]-DP[i-1][j-1];
            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
    }
}
