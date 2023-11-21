package blink75.binarysearch;

/// Smallest element such that Number ans < N

public class UpperBound {

  public static int[] ARR = {1,2,3,3,5,8,8,8,8,10,10,11};
  public static int N=8;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int high = ARR.length-1;
    int low = 0;
    int res = ARR.length;

    while (low<=high){
      int  mid = (low+high)/2;

      if(N < ARR[mid]){
        high = mid-1;
        res = mid;
      }else {
        low = mid+1;
      }

    }
    System.out.println("ANS : "+ res);
    System.out.println("ANS : ARR[res]: "+ ARR[res]);


  }
}

