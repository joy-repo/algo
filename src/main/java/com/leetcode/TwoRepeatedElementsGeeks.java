package com.leetcode;


//https://practice.geeksforgeeks.org/problems/two-repeated-elements-1587115621/1#

import java.util.Arrays;

public class TwoRepeatedElementsGeeks {

    public static void main(String[] args) {
        int arr[] ={1,2,1,3,4,3};
        int N=4;
        twoRepeated(arr,N);
    }

    public static int[] twoRepeated(int arr[], int N)
    {
        int[] res = new int[2];
        int k=0;
        for(int i=0; i<arr.length;i++){
            int j = Math.abs(arr[i]);
            if(arr[j]<0){
                res[k]=j;
                k++;
            } else {
                arr[j]=arr[j] *(-1);
            }
        }
        System.out.println(Arrays.toString(res));
        return res;
    }
}


