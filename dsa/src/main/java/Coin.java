public class Coin {

//  Write the code snippet to find the denomination of coins/notes with
  //  minimum count go make the given values.
//  Available denominations - 1, 2, 5, 10, 20

//  eg. 78 = 20 * 3 + 10 * 1 + 5 * 1 + 2 * 1 + 1 * 1
//      66 = 20 * 3 + 2 * 3

 // 78/20 = 3
  // 78%20 = 18
  // 18/10 = 1
  // 18%10 = 8
  // 8/5 = 1
  //8%5 = 3

  public static int[] DENO = { 1,2, 5, 10, 20, 100};
  public static int AMOUNT = 78;

  public static void main(String[] args) {
      solu();
  }

  private static void solu() {

    int res =0;
    int temp = AMOUNT;


    for(int i = DENO.length-1;i>=0;i--){
      int t = temp/DENO[i];
      if(t>0)
        System.out.println("Coin Deno <<"+DENO[i]+">> Coin Num :"+t);

      res = t+res;
      temp = temp%DENO[i];
      if(temp<=0) break;
    }

    if(temp==0){
      System.out.println("ANS :"+res);
      return;
    }
    System.out.println("Not Possible");

  }
}
