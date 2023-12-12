package blink75;

public class BuySellStocks {

  static int[] PRICE = {7,1,5,3,6,4};


  public static void main(String[] args) {

    int maxProfit=0;
    int minVal=PRICE[0];

    for(int i=0; i< PRICE.length; i++){

      if(PRICE[i]-minVal>maxProfit){
        maxProfit=PRICE[i]-minVal;
      }

      minVal = Math.min(PRICE[i],minVal);

    }
    System.out.println(maxProfit);

  }
}
