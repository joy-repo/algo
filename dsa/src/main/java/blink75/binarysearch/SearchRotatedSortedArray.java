package blink75.binarysearch;

public class SearchRotatedSortedArray {

  public static int[] ARR = {7,8,9,1,2,3,4,5,6};
  public static int N =8;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {
    int left =0;
    int right = ARR.length-1;


    while (left<=right){
      int mid  = (left+right)/2;

      if(ARR[mid]==N){
        System.out.println("ANS : "+ mid);
        System.out.println("ANS : ARR[mid]: "+ ARR[mid]);
        return;
      }

      // {7,8,9,1,2,3,4,5,6};

      if(ARR[mid]<N && N < right ){
        left=mid+1;
      }else {
        right = mid-1;
      }

    }
    System.out.println("NOT FOUND");
  }
}
