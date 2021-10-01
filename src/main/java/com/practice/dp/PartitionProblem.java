package com.practice.dp;

import java.util.Arrays;

public class PartitionProblem {

    static int ARR[] = {1, 5, 11, 5};
    static int SUM = Arrays.stream(ARR).sum();

    public static void main(String[] args) {
        boolean res = solByRec(0, 0, "");
        System.out.println(res);
        solByDP();
    }



    private static boolean solByRec(int tSum, int i, String str) {
        if (tSum == SUM / 2) {
            System.out.println(str);
            return true;
        }
        if (tSum > SUM / 2 || i == ARR.length) return false;

        return solByRec(tSum + ARR[i], i + 1, str + ARR[i] + ",") || solByRec(tSum, i + 1, str);
    }

    private static void solByDP() {
        boolean[][] DP = new boolean[ARR.length+1][(SUM / 2)+1];

//        for(int i=0; i<DP.length;i++)
//            DP[i][0]=true;

        for(int i=1; i<DP.length; i++){
            for(int j=1;j<DP[0].length; j++){
                if(ARR[i-1]>j)
                    DP[i][j]=DP[i-1][j];
                else if(ARR[i-1]==j)
                    DP[i][j]=true;
                else
                    DP[i][j]=DP[i-1][j-ARR[i-1]] || DP[i-1][j];
            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
    }
}
