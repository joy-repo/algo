package blink75;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LIS {

  static int[] ARR = {10,9,2,5,3,7,101,18};

  public static void main(String[] args) {
    int res =solRecc(0,0);
    System.out.println(res);
    solDP();
  }

  static void solDP(){
    int[] DP = new int[ARR.length];

    Arrays.fill(DP, 1);

    for(int i=0; i<DP.length-1; i++){
      for(int j=i+1; j< DP.length; j++){
        if(ARR[j]>ARR[i]) {
          DP[j] = Math.max(DP[j], DP[i] + 1);
        }
      }
    }

    System.out.println(Arrays.stream(DP).mapToObj(i-> new Integer(i)).collect(Collectors.toList()));

  }

  static int solRecc(int index, int count){

    if(index>= ARR.length-1) return count;

    int res=0;

    for(int i=index+1; i<ARR.length; i++){
      if(ARR[i]>ARR[index]){
        res = Math.max(solRecc(i, count+1), res);
      } else {
        res = Math.max(solRecc(i, 1), res);
      }
    }
    return res;

  }
}
