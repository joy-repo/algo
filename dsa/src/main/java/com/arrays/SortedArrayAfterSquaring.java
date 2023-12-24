package com.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortedArrayAfterSquaring {

  static int[] arr = {-3, -2, 0, 2, 4, 5};

  public static void main(String[] args) {

    sol();

  }

  private static void sol() {

    int i = 0;
    int j = arr.length-1;
    List<Integer> res = new ArrayList<>();

    int[] res1 = new int[arr.length];

    int c = arr.length-1;

    while(i<=j){

      int ni = Math.abs(arr[i]);
      int nj = Math.abs(arr[j]);

      if(ni<nj){
        res.add(nj*nj);
        res1[c]=nj*nj;

        j--;
      } else {
        res.add(ni*ni);
        res1[c]=ni*ni;
        i++;
      }
      c--;

    }
    System.out.println(res);
    //Arrays.asList(res1)
   // Arrays
    System.out.println(Arrays.asList(res1));
    Arrays.stream(res1).forEach(System.out::println);




  }
}
