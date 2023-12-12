package com.practice.tushar.dynamic;


import org.checkerframework.checker.units.qual.C;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//https://www.youtube.com/watch?v=Kf_M7RdHr1M&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=39
//Coin Changing- MINIMUM NumberOf coins
public class Prb39 {

    static int[] COINS= {1,2 ,3};
    static  int NUM= 8;

    public static void main(String[] args) {

       int res = Sol_REC(0);
       System.out.println(res);
      Map<Integer,Integer> map = new HashMap<>();
    //  map.put(0,0);
       res =Sol_REC_TOP_DOWN(0,map);
       System.out.println(map);
       System.out.println(res);

       sol_DP();
    }

    private static void sol_DP() {

        int[][] DP = new int[COINS.length+1][NUM+1];


        for(int i=1; i<=COINS.length;i++){
            for(int j=1; j<=NUM;j++){
                if(COINS[i-1]>j){
                    DP[i][j]=DP[i-1][j];
                } else {
                    DP[i][j]=DP[i][j-COINS[i-1]]+1;
                }
            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
    }

    private static int Sol_REC(int sum) {

        if(sum==NUM){
            return 0;
        }
        if(sum>NUM){
            return 2000;
        }
        int res = 2000;
        for(int c : COINS){
           res=Math.min(res, Sol_REC(sum+c)+1);
        }
        return res;
    }

    private static int Sol_REC_TOP_DOWN(int sum, Map<Integer,Integer> map) {


        if(sum>NUM) return 2000;
        if(sum==NUM){
            return 0;
        }

        if(map.containsKey(sum)) return map.get(sum);

        int res =2000;

        for(int c :COINS){
           res=Math.min(res, Sol_REC_TOP_DOWN(sum+c,map)+1);
        }
       map.put(sum,res);
       return map.get(sum);
    }



}
