package com.arrays;

//Find a pair with the given sum in an array

import java.util.Arrays;

public class PairWithConstantSum {

    static int[] A = { 8, 7, 2, 5, 3, 1 };
    static int sum =10;

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int l=0;
        int r =A.length-1;
        Arrays.sort(A);

        while ( l<r){

            if(sum==A[l]+A[r]){
                System.out.println(A[l]+","+A[r]);
                l++;
                r--;
                continue;
            }

            if(A[l]+A[r] > sum)
                r--;

            if(A[l]+A[r]<sum)
                l++;
        }

    }
}
