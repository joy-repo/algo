package blink75.dp;

/// https://www.youtube.com/watch?v=mLfjzJsN8us&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=3

import java.util.Arrays;

public class ClimbingStairs {

  static int N= 4;

  public static void main(String[] args) {
    int res= sol_RECC(0);
    System.out.println(res);

    int dp[] = new int[N];
    Arrays.fill(dp,-1);


    res=sol_MEM(0, dp);
    System.out.println(res);

    System.out.println("-----------DP----------");

    sol_DP();
  }

  private static void sol_DP() {

    int[] dp = new int[N+1];
    dp[0]=1;
    dp[1]=1;

    for( int i=2 ; i<=N ; i++){
      dp[i]=dp[i-1]+dp[i-2];
    }
    Arrays.stream(dp).sorted().forEach(System.out::println);
  }

  private static int sol_MEM(int i, int[] dp) {
    if(i==N) return 1;
    if(i>N) return 0;

    if(dp[i]!=-1) return dp[i];

    int res = sol_RECC(i+1)+sol_RECC(i+2);
    return dp[i] =res;

  }

  //         0
  //    1          2
  // 2      3    3    4
  //3  4  4  5 4
  //4 5

  private static int sol_RECC(int i) {

    if(i==N) return 1;
    if(i>N) return 0;

    int res = sol_RECC(i+1)+sol_RECC(i+2);
    return res;
  }
}
