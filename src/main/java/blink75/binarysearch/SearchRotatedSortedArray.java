package blink75.binarysearch;

public class SearchRotatedSortedArray {

  public static int[] ARR = {7,8,9,1,2,3,4,5,6};
  public static int N =8;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {
    int low =0;
    int high = ARR.length-1;


    while (low<=high){
      int mid  = (low+high)/2;

      if(ARR[mid]==N){
        System.out.println("ANS : "+ mid);
        System.out.println("ANS : ARR[mid]: "+ ARR[mid]);
        return;
      }

      if(ARR[mid]<N && N < high ){
        low=mid+1;
      }else {
        high = mid-1;
      }

    }
    System.out.println("NOT FOUND");
  }
}
