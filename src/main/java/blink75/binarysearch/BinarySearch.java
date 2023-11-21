package blink75.binarysearch;

public class BinarySearch {

  public static int[] ARR = {3,4,6,7,9,12,16};

  public static int N = 5;

  public static void main(String[] args) {
    sol();
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
