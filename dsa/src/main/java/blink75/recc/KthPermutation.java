package blink75.recc;



/// https://www.youtube.com/watch?v=wT7gcXLYoao&list=PLgUwDviBIf0rGlzIn_7rsaR2FQ5e6ZOL9&index=21
public class KthPermutation {

  static int N = 5;
  static int K = 7;

  static int count=0;

  public static void main(String[] args) {
    //sol_RECC();
  }

  private static void sol_RECC(int i, String str ) {
    if(i==N && count==K){
      System.out.println(str);
      return;
    }
    if(i==N){
      count++;
      return;
    }

//    for(int q=)

  }
}
