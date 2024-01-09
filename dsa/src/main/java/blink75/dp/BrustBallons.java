package blink75.dp;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BrustBallons {

  static int[] ARR = {3,1,5,8};

  public static void main(String[] args) {
    int[] store = new int[ARR.length];
    Arrays.fill(store, 1);
    sol_RECC(store);
  }

  private static void sol_RECC(int[] store) {

    int product = 1;

    for(int i= 0; i< store.length; i++){
      if(store[i]!=-1){
        getproduct(i, store);
      }
    }
  }

  private static void getproduct(int i, int[] store) {


  }
}
