package blink75;

public class Kadane {

  static int[] ARR = {-2,1,-3,4,-1,2,1,-5,4};

  public static void main(String[] args) {
    kadaneSol();
  }

  private static void kadaneSol() {

    int maxSum =0;
    int tempSum=0;

    for(int n : ARR){
      tempSum=Math.max(0, tempSum+n);
      maxSum= Math.max(maxSum, tempSum);

    }
    System.out.println(maxSum);

  }
}
