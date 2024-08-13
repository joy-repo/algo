package com.striver79.binary_serach;

public class MinimumInRotatedSortedArray {

    static int[] ARR = {4,5,6,7,0,1,2,3};

    public static void main(String[] args) {
        minimum();
    }

    private static void minimum() {

        int left = 0;
        int right = ARR.length-1;
        int res = Integer.MAX_VALUE;

        while (left<right){
            int mid =(left+right)/2;

            if(ARR[left]<=ARR[right]){
                res = Math.min(res, ARR[left]);
            }

            if(ARR[left] < ARR[mid]){
                left=mid+1;
            } else {
                right=mid-1;
            }

        }
        System.out.println(res);
    }
}
