package com.striver79.binary_serach;

// https://takeuforward.org/data-structure/peak-element-in-array/
public class IdentifyPeakElement {

    static int[] ARR = {1, 2, 3, 1 , 5, 6, 7, 8};

    public static void main(String[] args) {
        identifyPeak();
    }

    private static void identifyPeak() {

        int left = 1;
        int right = ARR.length-2;

        while (left <= right){

            int mid = (right+left)/2;

            if(ARR[mid-1] < ARR[mid] && ARR[mid] > ARR[mid+1]){
                System.out.println("ARR["+ mid + "] :" +ARR[mid]);
                return;
            }
            if(ARR[mid]>ARR[mid-1]){
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        System.out.println("no peak present");

    }
}
