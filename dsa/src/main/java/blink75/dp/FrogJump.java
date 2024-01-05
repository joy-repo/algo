package blink75.dp;

public class FrogJump {

  static int[] ARR = {10,20,30,10};

  public static void main(String[] args) {
   int res= solRecc(0);
   System.out.println(res);

   sol_DP();
  }

  private static void sol_DP() {

    int[] dp = new int[ARR.length];

    dp[0]=0;

    
  }

  private static int solRecc(int i) {

    if(i==ARR.length-1) return 0;

    if(i>= ARR.length) return -1;

    int res = Integer.MAX_VALUE;

    int res1 = solRecc(i+1) ;
    if(res1 != -1) {
      res1 = res1 + Math.abs(ARR[i] - ARR[i + 1]);
      res = Math.min(res, res1);
    }

    int res2 = solRecc(i+2) ;
    if(res2 != -1) {
      res2 = res2 + Math.abs(ARR[i] - ARR[i + 2]);
      res = Math.min(res, res2);
    }

    return res;


  }
}
