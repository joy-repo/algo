package blink75.dp;

public class SubSetSum {

  static int[] ARR = {3,1,1,2,1,1};
  static int SUM= 7;

  public static void main(String[] args) {
    boolean res =sol_RECC(0,0);
    System.out.println(res);
   sol_DP();
  }

  private static void sol_DP() {

    boolean DP[][] = new boolean[ARR.length+1][SUM];

    for(int r=1; r< ARR.length; r++){

      for(int c=0; c< SUM; c++){

        DP[r][c] = DP[r-1][c-ARR[r]] || DP[r-1][c];

      }
    }
  }

  private static boolean sol_RECC(int i, int sum) {

    if(i>=ARR.length) return false;
    if(sum==SUM) return true;
    //if(ARR[i]==SUM) return true;

   return  sol_RECC(i+1, sum+ARR[i]) || sol_RECC(i+1, sum);


  }
}
