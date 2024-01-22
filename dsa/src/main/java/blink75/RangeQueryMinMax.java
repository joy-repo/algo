package blink75;

public class RangeQueryMinMax {

  public static int[] ARR = {5,8,7,6,3};

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int[][] DP = new int[ARR.length][ARR.length];

    for(int i=0; i<ARR.length; i++){
      DP[i][i]=ARR[i];
    }

    for(int i =1 ; i< DP.length; i++) {
      for (int k = i; k < DP.length; k++) {
      // DP[1][2]               DP[1][1]        //DP[2][2]
        DP[k-i][k] = Math.min(DP[k-i][k-1], DP[k-i+1][k]);
      }
    }

    for (int i = 0; i < DP.length; i++) {
      System.out.println();
      for (int j = 0; j < DP[0].length; j++) {
        System.out.print(DP[i][j] + ",");
      }
    }

  }

}
