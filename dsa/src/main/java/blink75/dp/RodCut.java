package blink75.dp;

public class RodCut {

  static   int ARR[] = { 1, 5, 8, 9, 10, 17, 17, 20 };

  public static void main(String[] args) {
    int res = sol_RCCC(0);
    System.out.println(res);
  }

  private static int sol_RCCC(int i) {

    if(i>=ARR.length) return 0;

    int res = 0;

    for(int j =i+1 ; j<= ARR.length; j++){
      int temp = sol_RCCC(j)+ARR[j-i-1];
      res = Math.max(temp, res);
    }
    return res;

  }
}
