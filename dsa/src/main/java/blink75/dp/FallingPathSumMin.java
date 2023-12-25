package blink75.dp;

import java.util.Arrays;

/// https://www.youtube.com/watch?v=N_aJ5qQbYA0&list=PLg0aancPZwRazLXPEW-vu517p3gXVCn0b&index=12&t=405s
public class FallingPathSumMin {

  static int[][] ARR = {
      {1, 2, 10, 1},
      {5, 3, 2, 1},
      {1, 1, 20, 2},
      {1,2 ,2 ,1}
  };

  public static void main(String[] args) {

    System.out.println("-------RECURRSION ------");

    int res =Integer.MAX_VALUE;

    for(int i=0; i< ARR[0].length; i++)
      res = Math.min(solRecc(0, i), res);

    System.out.println(res);

    System.out.println();
    System.out.println("-------MEMORIZATION------");

    int[][] DP = new int[ARR.length][ARR[0].length];

    for (int[] temp : DP) {
      Arrays.fill(temp, -1);
    }

    res =Integer.MAX_VALUE;

    for(int i=0; i< ARR[0].length; i++)
      res = Math.min(sol_MEMORIZATION(0, i, DP), res);


    System.out.println(res);

    System.out.println();
    System.out.println("-------DP------");

    sol_DP();

  }

  private static void sol_DP() {

    int DP[][] = new int[ARR.length+1][ARR[0].length];

    for(int r = 1; r< DP.length; r++){

      for(int c =0; c < DP[0].length; c++){
        if(c==0){
          DP[r][c]= Math.min( DP[r-1][c],DP[r-1][c+1]) + ARR[r-1][c];
        } else  if( c==DP[0].length-1){
          DP[r][c]= Math.min( DP[r-1][c],DP[r-1][c-1]) + ARR[r-1][c];
        } else {
          DP[r][c] = Math.min(Math.min(DP[r - 1][c - 1], DP[r - 1][c]), DP[r - 1][c + 1]) + ARR[r - 1][c];
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

  private static int sol_MEMORIZATION(int row, int col, int[][] DP) {

    if(row>= ARR.length || col >= ARR[0].length || col<0 ) return Integer.MAX_VALUE;

    if(row== ARR.length-1) return ARR[row][col];

    if(DP[row][col]!=-1) return DP[row][col];

    int dl = solRecc(row+1, col-1);
    int d = solRecc(row+1, col);
    int dr = solRecc(row+1, col +1);

    DP[row][col] = Math.min(Math.min(dl, d),dr)+ ARR[row][col];
    return DP[row][col];



  }

  private static int solRecc(int row, int col) {

    if(row>= ARR.length || col >= ARR[0].length || col<0 ) return Integer.MAX_VALUE;

    if(row== ARR.length-1) return ARR[row][col];

    int dl = solRecc(row+1, col-1);
    int d = solRecc(row+1, col);
    int dr = solRecc(row+1, col +1);

    int res = Math.min(Math.min(dl, d),dr)+ ARR[row][col];
    return res;

  }

}
