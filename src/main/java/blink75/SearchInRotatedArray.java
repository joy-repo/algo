package blink75;

public class SearchInRotatedArray {

  static int ARR[] = {4,5,6,7,0,1};
  static int TARGET = 5;

  public static void main(String[] args) {
    System.out.println(solve());
  }
  static boolean solve(){
    int min = 0;
    int max = ARR.length-1;

    while(min<=max){
      int mid = (min+max)/2;
      if(ARR[mid] == TARGET){
        return true;
      }
      //Left Sorted
      if(ARR[min] <= ARR[mid]){
        if(ARR[min]<=TARGET && TARGET<=ARR[mid]){
            max=mid-1;
        } else {
            min=mid+1;
        }
      } else {  // Right Sorted
        if(ARR[mid]<=TARGET && TARGET<=ARR[max]){
          max=mid+1;
        } else {
          min=mid-1;
        }
      }
    }

    return false;
  }
}
