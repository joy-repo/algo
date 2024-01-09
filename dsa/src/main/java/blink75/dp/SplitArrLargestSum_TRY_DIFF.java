package blink75.dp;

// https://www.youtube.com/watch?v=thUd_WJn6wk

public class SplitArrLargestSum_TRY_DIFF {

  static int[] ARR = {10,20,30,40};
  static int K =2;

  public static void main(String[] args) {
    int res =sol_REC(0, K);
    System.out.println(res);
    sol_DPP();
  }

  private static void sol_DPP() {

    int[][] dp = new int [ARR.length][K];

    for(int r=0; r< ARR.length; r++){

      for (int c=0; c>=K; c++){
        int tsum =0;
        int res=(int)1e8;

        for(int i= r; i<ARR.length; i++){
          tsum=ARR[i]+tsum;

          int sumFromRec =dp[i-1][c-1];//sol_REC(i+1,c-1);

          int maxSum = Math.max(tsum, sumFromRec);
          res = Math.min(res,maxSum);

        }
        dp[r][c]=res;


      }
    }
    for (int i = 0; i < dp.length; i++) {
      System.out.println();
      for (int j = 0; j < dp[0].length; j++) {
        System.out.print(dp[i][j] + ",");
      }
    }
  }

  private static int sol_REC(int start, int numOfSubset) {

    if(start==ARR.length) return 0;

    if(numOfSubset==0){
      return getRemainingSum(start);
    }

    int tsum =0;
    int res=(int)1e8;

    for(int i= start; i<ARR.length; i++){
      tsum=ARR[i]+tsum;

      int sumFromRec =sol_REC(i+1,numOfSubset-1);

      int maxSum = Math.max(tsum, sumFromRec);
      res = Math.min(res,maxSum);

    }
    return res;
  }

  private static int getRemainingSum(int start) {
    int res=0;

    for(int i =start; i< ARR.length; i++){
      res=res+ARR[i];
    }
    return res;
  }
}
