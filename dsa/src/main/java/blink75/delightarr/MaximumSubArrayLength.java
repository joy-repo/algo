package blink75.delightarr;

import java.util.HashMap;
import java.util.Map;


//TODO : https://www.techiedelight.com/find-maximum-length-sub-array-having-given-sum/

public class MaximumSubArrayLength {

  static int[] ARR = { 5, 6, -5, 5, 3, 5, 3, -2, 0 };

  // 5 - 0
  // 11- 1
  // 6 - 2
  // 14 - 4
  // 19 - 5
  // 22 - 6
  // 20 - 7

  static int SUM =8;

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    Map<Integer, Integer> map = new HashMap<>();
    int currSum =0;

    int start = 0;
    int end=0;

    int res =0;

    for(int i =0;i<ARR.length; i++ ){

      currSum=currSum+ARR[i];

      if(currSum==SUM){
        res = Math.max(res,i );
      }

      map.putIfAbsent(currSum, i);

      if(map.containsKey(currSum-SUM)){
        int len = map.get(currSum-SUM);
        if(i-len > res){
          start =  map.get(currSum-SUM)+1;
          end = i;
          res=i-len;
        }
      }

    }

    System.out.println(res);
    System.out.println(start);
    System.out.println(end);
    for(int i = start; i<=end; i++){
      System.out.print(ARR[i] +",");
    }
  }
}
