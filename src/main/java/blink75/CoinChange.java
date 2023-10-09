package blink75;

import java.util.Arrays;
import java.util.stream.Collectors;

//LEETCODE 322
public class CoinChange {

  static int[] ARR = {1, 3,4,5};
  static int TARGET = 7;

  public static void main(String[] args) {
    //System.out.println(solRecc(0,0));

    solDP();
  }

  static void solDP(){

    int[] DP = new int[TARGET+1];
    DP[0]=0;
    for(int i=1; i<DP.length; i++)
      if(i%ARR[0]!=0)
        DP[i]=0;
      else
        DP[i] = i/ARR[0];

    for(int i =0; i<ARR.length; i++){
     // if(i<ARR[i]) continue;
      for(int j=1;j<DP.length; j++ ){
        if(j<ARR[i]) continue;
        DP[j]= Math.min(DP[j], DP[j-ARR[i]]+1);
      }
    }

    System.out.println(Arrays.stream(DP).mapToObj(i-> new Integer(i)).collect(Collectors.toList()));

    }


  static int solRecc(int sum, int count){

    if(sum== TARGET){
      return count;
    }
    if(sum> TARGET){
      return Integer.MAX_VALUE;
    }

    int res = Integer.MAX_VALUE;

    for(int n : ARR){
     res= Math.min(solRecc(sum+n, count+1), res);
    }

    return res;

  }
}
