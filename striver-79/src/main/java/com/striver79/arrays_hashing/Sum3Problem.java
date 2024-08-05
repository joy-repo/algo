package com.striver79.arrays_hashing;

import java.util.Arrays;

public class Sum3Problem {

    public static int[] arr = { -1, 0, 1, 2, -1, -4};

    static int SUM = 0;

    public static void main(String[] args) {
        sol1();
    }

    private static void sol1() {

        Arrays.sort(arr);

        for(int i = 0; i < arr.length; i++){
            int start = i+1;
            int end = arr.length-1;

            while (start<end){
                if(SUM < arr[i]+arr[start]+arr[end]){
                    end--;
                } else if (SUM > arr[i]+arr[start]+arr[end]){
                    start++;
                } else {
                    System.out.println("triplet : "+ arr[i] +", "+arr[start]+ ","+ arr[end]);
                    System.out.println("triplet indices: "+ i +", "+start+ ","+ end);
                    System.out.println("###########");
                    start++;
                    end--;
                }
            }
        }

    }
}
