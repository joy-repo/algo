package com.divide_conquer;


//https://www.techiedelight.com/search-element-circular-sorted-array/

public class SearchInCircularSortedArray {

    static int[] ARR = {7,8,9,10,1,2,4,5};
    static int TARGET = 9;

    public static void main(String[] args) {
        boolean res =solTIR();
        System.out.println(res);
    }

    private static boolean solTIR() {

        int start =0;
        int end=ARR.length-1;

        while(start<=end) {
            int mid = (end + start) / 2;
            if (ARR[mid] == TARGET) return true;

            if (TARGET > ARR[mid] && TARGET <= ARR[end])
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;



    }
}
