package com.divide_conquer;


//https://www.techiedelight.com/find-number-rotations-circularly-sorted-array/
//https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
public class CircularSortedArray {

    static int[] ARR = {7,8,9,1,2,3,4};

    public static void main(String[] args) {
       int res = solByITR();
       System.out.println(res);
    }

    private static int solByITR() {

        int start =0;
        int end=ARR.length-1;

        while(start<=end){
            int mid=(end+start)/2;

            if(mid==0) return 0;
            if(mid==ARR.length-1) return mid;

            if(ARR[mid-1]>ARR[mid] ) return mid;

            if(ARR[end]>ARR[mid]) end=mid-1;
            else start =mid+1;


        }
        return -1;

    }
}
