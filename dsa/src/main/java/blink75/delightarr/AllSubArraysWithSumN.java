package blink75.delightarr;

import java.util.HashMap;
import java.util.Map;

public class AllSubArraysWithSumN {

  static int[] ARR = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
  static int SUM = 0;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {
    // Sum, NumberOfTimes
    Map<Integer,Integer> map = new HashMap<>();

    int currSum=0;
    int res = 0;

    map.put(0,1);

    for (int i =0; i< ARR.length; i++){
      currSum=currSum+ARR[i];

//      if (currSum == SUM)
//        res++;

     if(map.containsKey(currSum-SUM)){
        res = res + map.get(currSum-SUM);
     }
     map.putIfAbsent(currSum, 0);
     map.put(currSum,map.get(currSum)+1);


    }
    System.out.println(res);


  }


}
