package blink75.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSort {

  public static int[] ARR = {38,27,43,10,11};

  public static void main(String[] args) {
    int res[] =sol(0, ARR.length-1);
    Arrays.stream(res).forEach(System.out::println);


  }

  private static int[] sol(int l, int r) {
    if(l==r){
      return new int[]{ARR[l]};
    }
    int mid = (l+r)/2;

    int[] arrLeft = sol(l, mid);
    int[] arrRight = sol(mid+1, r);

    int[] res = merge(arrLeft,arrRight);
    return res;
  }

  private static int[] merge(int[] arrLeft, int[] arrRight) {

    int i=0;
    int j=0;
    int res[] = new int[arrLeft.length+arrRight.length];
    int c=0;

    while (i<arrLeft.length && j < arrRight.length){

      if(arrLeft[i]<arrRight[j]){
        res[c]=arrLeft[i];
        i++;
        c++;
      } else {
        res[c]=arrRight[j];
        j++;
        c++;
      }

    }

    while (i<arrLeft.length){
      res[c]=arrLeft[i];
      c++;
      i++;
    }
    while (j<arrRight.length){
      res[c]=arrRight[j];
      c++;
      j++;
    }

    return res;

  }
}
