package com.practice.tushar.dynamic;

//0/1 Knapsack Problem Top Down Dynamic Programming
//https://www.youtube.com/watch?v=149WSzQ4E1g&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=36

import java.util.*;

public class Prb36 {

    static int[] VAL= {60,100,120};
    static int[] WT={10,20,30};
    static int totalWt=50;

    public static void main(String[] args) {

        sol();
        Map<Integer,Integer> map = new HashMap<>();
        int res =solRECC(0,0, map);

        System.out.println(res);
        System.out.println(map);
    }

    private static void sol() {

        int[][] DP=new int[VAL.length+1][totalWt+1];

        for(int i=1; i<=VAL.length;i++){
            for(int j=1; j<=totalWt;j++){
                if(WT[i-1]>j)
                    DP[i][j]=DP[i-1][j];
                else
                    DP[i][j]=Math.max(DP[i-1][j],DP[i-1][j-WT[i-1]]+VAL[i-1]);

            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
    }

    private static int solRECC(int wt, int n, Map<Integer,Integer> map) {



        if(wt>=totalWt || n>= WT.length) {return 0;}

        if(map.containsKey(wt))
            return map.get(wt);

        if(totalWt<wt+WT[n]) {
            int res= solRECC (wt, n + 1, map);
            map.put(wt,res);
            return res;
        }

        int incl = solRECC(wt+WT[n], n+1,map)+VAL[n];
        int excl = solRECC(wt,n+1,map);
        int res= Math.max(incl,excl);
        map.put(wt,res);
        return res;

    }


}
