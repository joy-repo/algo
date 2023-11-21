package blink75.binarysearch;

public class BinarySearch {

  public static int[] ARR = {3,4,6,7,9,12,16};

  public static int N = 12;

  public static void main(String[] args) {
    sol();
    int val = solRecc(0, ARR.length-1);
    System.out.println("RECC ans :"+ val);
  }

  private static int solRecc(int low, int high) {

    if(low>high){
      return -1;
    }
    int mid = (low+high)/2;
    if(ARR[mid]==N){
      return mid;
    }
    if(N > ARR[mid]){
      return solRecc(mid+1, high);
    }else {
      return solRecc(low, mid-1);
    }

  }

  private static void sol() {
    int high = ARR.length-1;
    int low=0;

    while (low<=high){
      int mid = (low+high)/2;

      if(N== ARR[mid]){
        System.out.println("ans : "+ mid);
        return;
      }
      if(N < ARR[mid]){
        high = mid-1;
      }
      if(N > ARR[mid]){
        low = mid+1;
      }
    }
    System.out.println("NOT FOUND");

  }
}
