package blink75.dp;

//https://www.codingninjas.com/studio/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos

///Striver DP-7



public class NinjaTraining {


  static int ARR[][] = {
      {1,2,5},
      {3,1,1},
      {3,3,3}
  };

  public static void main(String[] args) {
//    int res = solRecc(0, -1);
//    System.out.println(res);

//    int[][] DP = new int[ARR.length][ARR[0].length];
//    System.out.println("------------------------");
//
//    int rr =sol_DP_Memorization(0, -1, DP);
//    System.out.println(rr);

    sol_DP();
  }

  private static void sol_DP() {

    int[][] DP = new int[ARR.length][ARR[0].length];

    for (int i = 0; i < ARR[0].length; i++) {
      DP[0][i] = ARR[0][i];
    }

    for (int r = 1; r < ARR.length ; r++) {

      for (int c = 0; c < ARR[0].length; c++) {
        int max = 0;
        for (int c1 = 0; c1 < ARR[0].length ; c1++) {
          if (c1 != c) {
            max = Math.max(DP[r-1][c1], max);
          }
        }
        DP[r][c] = max+ARR[r][c];

      }
    }
    for (int i = 0; i < ARR.length; i++) {
      System.out.println();
      for (int j = 0; j < ARR[0].length; j++) {
        System.out.print(DP[i][j] + ",");
      }
    }
  }


  private static int sol_DP_Memorization(int day, int lastTaskIndex, int[][] DP) {

    if(DP[day][lastTaskIndex]!=0) return DP[day][lastTaskIndex];

    if(day== ARR.length-1){
      int max = 0;
      for(int i=0; i<ARR[day].length; i++){
        if(i!=lastTaskIndex) max = Math.max(max, ARR[day][i]);
      }
      return max;
    }

    int max =0;

    for(int i =0 ; i<ARR[0].length; i++){
      if(i!=lastTaskIndex){
        int points = ARR[day][i]+solRecc(day+1, i);
        max = Math.max(max, points);
      }
    }
    DP[day][lastTaskIndex] = max;
    return DP[day][lastTaskIndex];


  }

  private static int solRecc(int day, int lastTaskIndex) {

    if(day== ARR.length-1){
      int max = 0;
      for(int i=0; i<ARR[day].length; i++){
        if(i!=lastTaskIndex) max = Math.max(max, ARR[day][i]);
      }
      return max;
    }

    int max =0;
    for(int i =0 ; i<ARR[0].length; i++){
      if(i!=lastTaskIndex){
        int points = ARR[day][i]+solRecc(day+1,i ) ;
        max = Math.max(max, points);
      }
    }
    return max;


  }
}
