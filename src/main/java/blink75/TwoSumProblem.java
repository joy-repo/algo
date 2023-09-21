package blink75;

import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {

  public static int[] ARR = {2, 6, 5, 8, 11};
  public static int TARGET=14;

  public static void main(String[] args) {

    solve();

  }

  private static void solve() {
    Map<Integer, Integer> map = new HashMap<>();
    for(int i=0; i<ARR.length; i++){

      if(map.containsKey(TARGET-ARR[i])){
        System.out.println("Indices-<<"+i+">> and <<"+map.get(TARGET-ARR[i])+">>");
      }

    map.put(ARR[i], i);

    }

  }
}
