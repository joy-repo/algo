package blink75.binarysearch;

public class LeastInRotatedSortedArray {

  public static int[] ARR = {7,8,9,10,12,14, 20, 1,2,3,4,5,6};

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int left=0;
    int right = ARR.length-1;

    while (left<=right){
      int mid = (left+right)/2;

      if(ARR[mid]>left && ARR[mid]>right){
        left=mid+1;
      } else {
        right = mid-1;
      }
    }
    int res = Math.min(ARR[right], ARR[left]);
    System.out.println("right :"+right);
    System.out.println("left :"+left);
    System.out.println(ARR[left]);

  }

}
