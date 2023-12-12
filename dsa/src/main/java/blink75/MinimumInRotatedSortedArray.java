package blink75;

public class MinimumInRotatedSortedArray {

  static int ARR[] = {4,5,6,7,0,1};

  public static void main(String[] args) {
    solve();
  }

  private static void solve(){
    int max = ARR.length-1;
    int min=0;
    while (Math.abs(max-min)>1){
      if(ARR[(min+max)/2] > ARR[max]){
        min = (min+max)/2;
      } else {
        max=(min+max)/2;
      }
    }
    int res = Math.min(ARR[min], ARR[max]);
    System.out.println(res);
  }
}
