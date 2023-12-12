package blink75.binarysearch;

public class FindPeakElement {

  public static int[] ARR = {9,1,2,3,4,5,6,7,8, 12};

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int low=0;
    int high = ARR.length-1;

    while (low<=high){
      int mid = (low+high)/2;
      if(mid==0 && ARR[mid] > ARR[mid+1]){
        System.out.println("ANS :"+ mid);
        System.out.println("ANS ARR[mid]:"+ ARR[mid]);
        return;
      }
      if(mid==ARR.length-1 && ARR[mid] > ARR[mid-1]){
        System.out.println("ANS :"+ mid);
        System.out.println("ANS ARR[mid]:"+ ARR[mid]);
        return;
      }
      if(ARR[mid]>ARR[mid+1] && ARR[mid]>ARR[mid-1]){
        System.out.println("ANS :"+ mid);
        System.out.println("ANS ARR[mid]:"+ ARR[mid]);
        return;
      }

      if(ARR[mid]>ARR[mid-1] && ARR[mid+1]>ARR[mid]){
        low=mid+1;
      } else {
        high = mid-1;
      }
    }

  }
}
