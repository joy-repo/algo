package blink75.dp;

///https://www.youtube.com/watch?v=SrP-PiLSYC0&list=PLg0aancPZwRazLXPEW-vu517p3gXVCn0b&index=11

import java.util.Arrays;

public class MinimumSumPathTraingle {

  static int[][] ARR = {
      { 1 },
      { 2, 3 },
      { 3, 6, 1 },
      { 8, 9, 6, 1 }
  };

  public static void main(String[] args) {

    System.out.println("-------RECURRSION ------");
    int res = solRecc(0, 0);
    System.out.println(res);

    System.out.println();
    System.out.println("-------MEMORIZATION------");

    int[][] DP = new int[ARR.length][ARR[ARR.length-1].length];

    for (int[] temp : DP) {
      Arrays.fill(temp, -1);
    }

    int result = solRecc_MEMORIZATION(0, 0, DP);

    System.out.println(result);
    System.out.println();
    System.out.println("-------DP------");

    sol_DP();

  }

  private static void sol_DP() {

    int[][] DP = new int[ARR.length][ARR[ARR.length-1].length];
    for (int[] temp : DP) {
      Arrays.fill(temp, -1);
    }

    for(int r=0; r< ARR.length; r++){
      for (int c=0; c<=r; c++){
        if(r>0 && c>0){
          if(DP[r-1][c]!=-1)
            DP[r][c] = Math.min(DP[r-1][c-1], DP[r-1][c])+ARR[r][c];
          else
            DP[r][c] = DP[r-1][c-1]+ARR[r][c];
        } else if(r>0 && c==0){
          DP[r][c] = DP[r-1][c]+ARR[r][c];
        } else if (r==0 && c==0){
          DP[0][0]=ARR[0][0];
        }
      }
    }
    for (int i = 0; i < DP.length; i++) {
      System.out.println();
      for (int j = 0; j < DP[0].length; j++) {
        System.out.print(DP[i][j] + ",");
      }
    }

  }

  private static int solRecc_MEMORIZATION(int r, int c, int[][] dp) {

    if (c >r)
      return Integer.MAX_VALUE;
    if (r >= ARR.length)
      return Integer.MAX_VALUE;

    if (r == ARR.length - 1)
      return ARR[r][c];

    if(dp[r][c]!= -1) return dp[r][c];

    dp[r][c] = Math.min(solRecc_MEMORIZATION(r + 1, c + 1, dp), solRecc_MEMORIZATION(r + 1, c, dp)) + ARR[r][c];
    return dp[r][c];
  }

  private static void sol_DPPP(){

    int dp[][] = new int[ARR.length][ARR.length];
    dp[0][0]=ARR[0][0];

    for (int r=1; r< ARR.length; r++){
      for (int c=0; c<=r; c++){
        if(c>0)
          dp[r][c] = Math.min(dp[r-1][c-1], dp[r-1][c]) + ARR[r][c];
        else
          dp[r][c] =  dp[r-1][c] + ARR[r][c];


      }
    }

  }

  private static int solRecc(int r, int c) {

    if (c > r )
      return Integer.MAX_VALUE;
    if (r >= ARR.length)
      return Integer.MAX_VALUE;

    if (r == ARR.length - 1)
      return ARR[r][c];

    return Math.min(solRecc(r + 1, c + 1), solRecc(r + 1, c)) + ARR[r][c];

  }
}
