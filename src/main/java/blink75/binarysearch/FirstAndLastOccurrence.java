package blink75.binarysearch;

import java.io.PrintStream;

// TODO: https://www.youtube.com/watch?v=hjR1IYVx9lY&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=4
public class FirstAndLastOccurrence {

  public static int[] ARR = { 2, 4, 6,8,8,8, 11, 13};
  public static int N =8;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    // LOWERBOUND and UPPRBOUND-1

    //LOWERBOUNND
    int startIndex = lowerbound();
    int lastIndex =upperBound()-1;

    System.out.println("startIndex : "+ startIndex +"  lastIndex = "+ lastIndex);

  }

  private static int upperBound() {

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
    return res;


  }

  private static int lowerbound() {

    int high = ARR.length - 1;
    int low = 0;
    int res = ARR.length;

    while (low <= high) {
      int mid = (low + high) / 2;

      if (N <= ARR[mid]) {
        high = mid - 1;
        res = mid;
      } else {
        low = mid + 1;
      }

    }
    System.out.println("ANS : " + res);
    System.out.println("ANS : ARR[res]: " + ARR[res]);
    return res;
  }

}
