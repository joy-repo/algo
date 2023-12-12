package com.arrays;

import java.util.Arrays;

public class SUMOF3 {

    static int[] array = {12, 3, 4, 1, 6, 9};
    static int sum=24;

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        Arrays.sort(array);

        for (int i=0; i< array.length-1; i++){
            int s =i+1;
            int e =array.length-1;

            while(s<e){
                if(sum==array[i]+array[s]+array[e]){
                    System.out.println(array[i]+","+array[s]+","+array[e]);
                   return;
                } else if(sum>array[i]+array[s]+array[e]){
                    s++;
                } else{
                    e--;
                }

            }
        }
    }
}
