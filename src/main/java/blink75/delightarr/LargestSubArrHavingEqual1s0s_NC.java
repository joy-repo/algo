package blink75.delightarr;


//TODO: https://www.techiedelight.com/find-maximum-length-sub-array-equal-number-0s-1s/

import java.util.HashMap;
import java.util.Map;

public class LargestSubArrHavingEqual1s0s_NC {

  static int[] ARR = { 0, 0, 1, 0, 1, 0, 0 };

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    int res=0;
    ///  0s, ith position
    Map<Integer, Integer> map0 = new HashMap<>();
    ///  1s, ith position
    Map<Integer, Integer> map1 = new HashMap<>();

    int count0 =ARR[0]==0 ? 1: 0 ; /// 0s-1s
    int count1 =ARR[0]==1 ? 1: 0;
    map0.put(count0, 1 );
    map1.put(count1, 1 );
    for(int i=1; i< ARR.length; i++){
      if(ARR[i]==0){
        count0++;
      } else {
        count1++;
      }
      map0.putIfAbsent(count0, 0);
      map0.put(count0, map0.get(count0)+1);

      map1.putIfAbsent(count1, 0);
      map1.put(count1, map1.get(count1)+1);

     if(count0==count1){
      res++;
     }

//     if(map0.containsKey(map1.get())){
//       res = map.get(0-count)+res;
//     }
    }
    System.out.println(res);


  }
}
