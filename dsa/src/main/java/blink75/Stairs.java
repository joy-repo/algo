package blink75;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Stairs {

  static int N=10;
  // Steps 1 or 2

  static int RES=0;

  public static void main(String[] args) {
    sol(1);
    System.out.println(RES);
    System.out.println(sol2(1));
    System.out.println("#############DP##########");
    solDP();
  }

  static void solDP(){

    int[] DP = new int[N+1];

    DP[0]=1;
    DP[1]=1;


    for(int i=2; i<=N; i++){
      DP[i]= DP[i-1]+DP[i-2];
    }
    System.out.println(Arrays.stream(DP).mapToObj(i-> new Integer(i)).collect(Collectors.toList()));

  }

  static int sol2(int n){
    if(n==N) return 1;
    if(n>N) return 0;
    return sol2(n+1) + sol2(n+2);
  }

  static void sol(int n){

    if(n==N) RES++;
    if(n>=N) return;

   sol(n+1);
   sol(n+2);

  }
}
