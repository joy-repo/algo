package blink75;

public class CountingBitsLeetCode338 {


  static int N = 25;

  //1, 2,3 ,4 ,5 ,6 ,7 ,8 ,9,10

  // 0000 -0-0
  // 0001 -1-1
  // 0010 -2-1
  // 0011 -3-2
  // 0100 -4-1
  // 0101 -5-2
  // 0110 -6-2
  // 0111 -7-3
  // 1000 -8-1
  public static void main(String[] args) {

    int[] DP = new int[26];
    DP[0]=0;
    DP[1]=1;

    for(int i =2; i<=N; i++){
      if(i%2==0)
        DP[i] = DP[i/2];
      else
        DP[i] = DP[i/2]+1;
    }

    System.out.println(DP);

  }
}
