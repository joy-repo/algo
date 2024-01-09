package blink75.dp;

///https://www.youtube.com/watch?v=PhWWJmaKfMc

import java.util.Arrays;

public class PartitionArrayForMaxSum {

  static int K=3;

  static int[] ARR = {1, 15, 7, 9, 2, 5, 10 };
    // tem =1  // max =15
  ///  ////////////// 1, 15  , 15, 0, 0, 0, 0

  public static void main(String[] args) {
    sol_RECC(-1);

    int[] dp = new int[ARR.length];
    Arrays.fill(dp , -1);
    sol_RECC_MEMO(-1, dp);

    sol_DP();
  }

  private static void sol_DP() {

    int[] dp = new int[ARR.length];
    dp[0]=ARR[0];

    for(int j =1; j< ARR.length; j++ ){

      int max = dp[j-1];
      int len=0;

      for(int i = j ; i<=j+K && i<ARR.length; i++){
        max = Math.max(max, ARR[i]);
        len++;
        int temp = max*len + dp[j];
        dp[i] = Math.max(dp[i], temp);
      }

    }
  }

  private static int sol_RECC_MEMO(int st, int[] dp) {

    if(st>=ARR.length) return 0;

    if(dp[st]!=-1) return dp[st];

    int res = -1;
    int max= -1;

    for(int i = st+1 ; i<=st+K && i<ARR.length; i++){
      max = Math.max(max, ARR[i]);
      int temp = max*(i-st) + sol_RECC(i);
      res = Math.max(res, temp);
    }
    return dp[st]=res;
  }

  private static int sol_RECC(int st) {

    if(st>=ARR.length) return 0;

    int res = -1;
    int max= -1;

    for(int i = st+1 ; i<=st+K && i<ARR.length; i++){
      max = Math.max(max, ARR[i]);
      int temp = max*(i-st) + sol_RECC(i);
      res = Math.max(res, temp);
    }
    return res;

  }
}
