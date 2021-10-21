package com.arrays;

//Find maximum sequence of continuous 1s formed by replacing at-most k zeroes by ones

public class MaxSequenceOfContinuous1 {

    public static int[] arr = {1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1,1};
    public static int K = 2;

    public static void main(String[] args) {
        solByDP();
        sol_slidingWindow();
    }

    public static void solByDP() {

        int count0 = 0;
        int[] count0Store = new int[arr.length];
        int start = 0;
        int maxLen = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                count0++;
                count0Store[count0] = i;
            }

            if (count0 > K && arr[i] == 0)
                start = count0Store[count0 - K] + 1;

            maxLen = Math.max(maxLen, i - start + 1);

        }
        System.out.println(maxLen);
    }

    public static void sol_slidingWindow() {


        int left = 0;
        int right = 0;
        int res = 0;
        int replaced0s = 0;
        int maxcount = 0;

        while (right < arr.length && left < arr.length) {

            if (arr[right] == 1) {
                maxcount++;
                right++;
                continue;
            }

            if (arr[right] == 0) {
                replaced0s++;
                maxcount++;
            }

            if (replaced0s == K) {
                res = Math.max(res, maxcount);
            }

            while (replaced0s > K) {
                if (arr[left] == 0) {
                    replaced0s--;
                }
                left++;
                maxcount--;
            }
            if (replaced0s == K) {
                res = Math.max(res, maxcount);
            }

            right++;
        }
        res = Math.max(res, maxcount);
        System.out.println(res);

    }

}
