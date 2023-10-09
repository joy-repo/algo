package blink75;

public class NumberOf1bits {

  static int N =22;

  public static void main(String[] args) {
    System.out.println(Integer.toBinaryString(N));
    System.out.println(Integer.toBinaryString(N & (N-1)));
    System.out.println("/////SOL1/////");
    sol1();
    System.out.println("/////SOL2/////");
    sol1();
  }

  //// AND-ing N and N-1 flips the last significant 1 bit to 0
  /// That is 22 - 10110
  /// 22 & (22-1) - 10100 last significan bit from 1 to 0.
  static void sol1(){
    int res =0;
    int n =N;
    while(n!=0){
      n = n & (n-1);
      res++;
    }
    System.out.println(res);
  }

  //AND ding with poswe of 2
  // that is 2, 4, 8, 16...
  static void sol2(){
    int power2 = 2;
    int res =0;
    for(int i =0 ; i<32; i++){
      if((power2&N)!=0)
        res++;
      power2 = power2 <<1;

    }
    System.out.println(res);

  }
}
