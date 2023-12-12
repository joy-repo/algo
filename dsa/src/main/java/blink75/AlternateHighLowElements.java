package blink75;

import java.util.Arrays;

public class AlternateHighLowElements {

  static int[] ARR = {9, 6, 8, 3, 7, 10};

  //3, 6,7,8,9

  public static void main(String[] args) {
    sol();

    for( int i : ARR){
      System.out.println(i);
    }
  }

  private static void sol() {

    Arrays.sort(ARR);
    int len =ARR.length;
    if(ARR.length%2!=0) len = ARR.length-1;

    for(int i=0; i<len; i=i+2){
      int tmp = ARR[i];
      ARR[i]=ARR[i+1];
      ARR[i+1]=tmp;
    }



  }
}
