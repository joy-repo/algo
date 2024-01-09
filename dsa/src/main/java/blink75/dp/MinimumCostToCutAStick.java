package blink75.dp;

//https://leetcode.com/problems/minimum-cost-to-cut-a-stick/
//https://www.youtube.com/watch?v=xwomavsC86c&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=51

import java.util.Arrays;

public class MinimumCostToCutAStick {

  static int N =7;
  static int[] ARR = {1,3,4,5};

  public static void main(String[] args) {

   int res = sol_RCC(0,N);
   System.out.println(res);

   solDP();

  }

  private static void solDP() {

    int[][] dp = new int[N][N+1];

    for(int strt = 0; strt<N; strt++){
      for (int end=N; end>0; end--){
        int min = Integer.MAX_VALUE;
        for(int cutIndx : ARR){
          if(cutIndx>strt && cutIndx<end){
            int temp = end-strt + dp[strt][cutIndx]+dp[cutIndx][end];
            min= Math.min(temp,min);
          }
        }
        if(min!=Integer.MAX_VALUE)
          dp[strt][end]=min;

      }
    }

    for (int i = 0; i < dp.length; i++) {
      System.out.println();
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + ",");
      }
    }
  }

  private static int sol_RCC(int strt, int end) {

    if(end==strt) return 0;

    int res = (int)1e8;

    for(int cutIndx : ARR){
      if(cutIndx>strt && cutIndx<end){
        int temp = end-strt + sol_RCC(strt, cutIndx)+sol_RCC(cutIndx, end);
        res= Math.min(temp,res);
      }
    }
    if(res==(int)1e8) res=0;
    return res;


  }

}
