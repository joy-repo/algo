package blink75;

public class MaxProductSubArray {


  static int[] ARR = {1,2,-3,0,-4,-5};

  public static void main(String[] args) {
    kadaneProd();
  }


  private static void kadaneProd() {

    int maxProd =1;
    int tempMinProd=ARR[0];
    int tempMaxProd=ARR[0];

    for(int n : ARR){
      int temp = Math.min(Math.min(tempMaxProd*n, tempMinProd*n), n);
      tempMaxProd = Math.max(Math.max(tempMaxProd*n, tempMinProd*n), n);
      tempMinProd=temp;
      maxProd = Math.max(Math.max(tempMaxProd, tempMinProd),maxProd);


    }
    System.out.println(maxProd);

  }
}

