package blink75.binarysearch;

public class LeastInRotatedSortedArray {

  public static int[] ARR = {7,8,9,10,12, 1,2,3,4,5,6};

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int low=0;
    int high = ARR.length-1;

    while (low<=high){
      int mid = (low+high)/2;

      if(ARR[mid]>low && ARR[mid]>high){
        low=mid+1;
      } else {
        high = mid-1;
      }
    }
    int res = Math.min(ARR[high], ARR[low]);
    System.out.println("high :"+high);
    System.out.println("low :"+low);
    System.out.println(ARR[low]);

  }

}
