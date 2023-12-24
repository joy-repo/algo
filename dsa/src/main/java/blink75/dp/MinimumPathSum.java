package blink75.dp;

import java.util.Arrays;

public class MinimumPathSum {

  static int[][] ARR = {
      {5, 9,6},
      {11, 5, 2}
  };

  public static void main(String[] args) {

    System.out.println("-------RECURRSION ------");
    int res  = solRecc(0,0);
    System.out.println(res);
    System.out.println("-------DP------");

    sol_DP();
    System.out.println();
    System.out.println("-------MEMORIZATION------");

    int[][] DP = new int[ARR.length][ARR[0].length];

    for (int[] temp : DP) {
      Arrays.fill(temp, -1);
    }

    int result = solRecc_MEMORIZATION(0, 0, DP);

    System.out.println(result);
  }

  private static int solRecc_MEMORIZATION(int r, int c, int[][] DP) {

    if(r>= ARR.length || c>=ARR[0].length) return Integer.MAX_VALUE;

    if(r== ARR.length-1 && c==ARR[0].length-1) return ARR[r][c];

    if(DP[r][c]!=-1) return DP[r][c];

    DP[r][c] = Math.min(solRecc_MEMORIZATION(r+1, c,DP),solRecc_MEMORIZATION(r,c+1,DP) )+ ARR[r][c];
    return DP[r][c];


//    if(r>=ARR.length || c >= ARR[0].length) return Integer.MAX_VALUE;
//
//    if(r== ARR.length-1 && c==ARR[0].length-1) return ARR[r][c];
//
//    return Math.min(solRecc(r+1, c), solRecc(r,c+1)) + ARR[r][c];
  }

  private static void sol_DP() {

    int[][] DP = new int[ARR.length][ARR[0].length];

    for(int r=0; r< ARR.length; r++){
      for (int c=0; c<ARR[0].length; c++){
        if(r>0 && c>0){
          DP[r][c] = Math.min(DP[r-1][c], DP[r][c-1])+ARR[r][c];
        } else if(r>0){
          DP[r][c] = DP[r-1][c]+ARR[r][c];
        } else if(c>0){
          DP[r][c] = DP[r][c-1]+ARR[r][c];
        } else {
          DP[r][c]=ARR[r][c];
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

  private static int solRecc(int r, int c) {

    if(r>=ARR.length || c >= ARR[0].length) return Integer.MAX_VALUE;

    if(r== ARR.length-1 && c==ARR[0].length-1) return ARR[r][c];

    return Math.min(solRecc(r+1, c), solRecc(r,c+1)) + ARR[r][c];


  }


}
