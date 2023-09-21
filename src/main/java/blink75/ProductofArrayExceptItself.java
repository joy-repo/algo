package blink75;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProductofArrayExceptItself {

  static int ARR[]  = {10, 3, 5, 6, 2};

  public static void main(String[] args) {
    int product = Arrays.stream(ARR).reduce(1, (e1,e2)-> e1*e2);
    int[] res = new int[ARR.length];
    for(int i =0; i<ARR.length; i++){
      res[i]=product/ARR[i];
    }
    System.out.println(Arrays.stream(res).mapToObj(i-> new Integer(i)).collect(Collectors.toList()));
  }
}
