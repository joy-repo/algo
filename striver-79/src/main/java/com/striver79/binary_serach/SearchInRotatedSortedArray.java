package com.striver79.binary_serach;


// https://takeuforward.org/arrays/search-element-in-rotated-sorted-array-ii/
public class SearchInRotatedSortedArray {

    static int[] ARR = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
    static int K = 3;

    public static void main(String[] args) {
        search();
    }

    private static void search() {

        int left = 0;
        int right = ARR.length-1;



        while(left<right){

            int mid = (left+right)/2;

            if(ARR[mid]==K){
                System.out.println("index : "+ mid);
                return;
            }

            if(ARR[mid]<K && K < ARR[left]){
                left = mid+1;
            } else if(K > ARR[right]){
                right=mid-1;
            }



        }
    }

}
