package blink75.delightarr;

import java.util.HashMap;
import java.util.Map;

public class AllPairsWithGivenSum {

  static int[] ARR =  {8, 7, 2, 5, 3, 1};
  static int SUM = 10;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    Map<Integer, Integer> map = new HashMap<>();

    for(int i = 0; i< ARR.length; i++){

      if(map.containsKey(SUM-ARR[i])){
        System.out.println(map.get(SUM-ARR[i]) +","+ i);
      }
      map.put(ARR[i], i);

    }
  }

}
