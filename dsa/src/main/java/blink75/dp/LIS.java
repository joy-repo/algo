package blink75.dp;

public class LIS {

  static int [] ARR = {3, 10, 2, 1, 20};

  public static void main(String[] args) {
    int res = sol_RCC(0);
    System.out.println(res);

    sol_DP();
  }

  private static void sol_DP() {

    int [] DP = new int[ARR.length];

    for( int i =0; i< ARR.length; i++){
      int res =1;
      for(int j=0; j< i;j++ ){
        if(ARR[i]<ARR[j]){
          res = Math.max(res,DP[j]+1);
        } else {
          res = Math.max(res,DP[j]);
        }
      }
      DP[i]=res;

    }

    for(int i : DP){
      System.out.println(i);
    }

  }

  private static int sol_RCC(int i) {

    if(i>=ARR.length) return 0;

    int res =1;

    for(int j=i+1; j< ARR.length;j++ ){
      if(ARR[i]<ARR[j]){
        res = Math.max(res,sol_RCC(j)+1);
      } else {
        res = Math.max(res,sol_RCC(j));
      }
    }
    return res;

  }
}
