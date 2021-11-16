package com.arrays;

//https://www.geeksforgeeks.org/maximum-length-bitonic-subarray/
public class LongestBitonicSubArray {

    static int[] Arr = {20, 4, 1, 2, 3, 4, 2, 10};

    public static void main(String[] args) {
        longestBitonicSubArr();
    }

    private static void longestBitonicSubArr() {

        boolean increasing = false;
        boolean decreasing = false;
        int inrLen = 0;
        int dcrLen = 0;
        int maxLen = 0;
        int start = 0;
        int end = 0;
        int maxStart = 0;
        int maxEnd = 0;
        if (Arr[0] < Arr[1]) {
            increasing = true;
            inrLen = 1;
        } else {
            decreasing = true;
            dcrLen = 1;
        }
        int prev = Arr[0];
        for (int i = 1; i < Arr.length; i++) {

            if (prev < Arr[i] && increasing)
                inrLen++;


            if (prev > Arr[i] && decreasing)
                dcrLen++;

            if (prev > Arr[i] && increasing) {
                decreasing = true;
                increasing = false;
                dcrLen = 1;
            }

            if (prev < Arr[i] && decreasing) {
                if (inrLen + dcrLen > maxLen) {
                    maxLen = inrLen + dcrLen;
                    maxStart = start;
                    maxEnd = i - 1;
                }
                inrLen = 2;
                dcrLen = 0;
                decreasing = false;
                increasing = true;
                start = i - 1;
            }

            prev = Arr[i];
        }

        if (inrLen + dcrLen > maxLen) {
            maxLen = inrLen + dcrLen;
            maxStart = start;
            maxEnd = Arr.length - 1;
        }
        System.out.println(maxLen);

    }
}
