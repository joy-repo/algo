package blink75;

public class SumOf2Int {

  static int A= 5;
  static int B = 7;

  public static void main(String[] args) {
   int a = 5; // 0101
   int b =7; //0111
    sol();

    //a & b = 0101
    int c = a&b;
   System.out.println(c);
   int d = c<<1;
   System.out.println(Integer.toBinaryString(c));
   System.out.println(Integer.toBinaryString(d));
  // System.out.println(c<<1);

  }

  static void sol(){
    while(B!=0){
      int carry = A&B;
      int temp = carry << 1;
      A = A^B;
      B=temp;
    }
    System.out.println(A);
  }
}
