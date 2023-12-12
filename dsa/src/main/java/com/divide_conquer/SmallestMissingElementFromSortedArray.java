package com.divide_conquer;


//https://www.techiedelight.com/find-smallest-missing-element-sorted-array/

public class SmallestMissingElementFromSortedArray {

    static int[] ARR = {0, 1, 2, 3, 4, 5, 6};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int strt = 0;
        int end = ARR.length - 1;
        int res = 0;

        while (end >= strt) {
            int mid = end + strt;
            if (ARR[mid] == mid) {
                strt = mid + 1;
                res = ARR[mid];
            } else {

                end = mid - 1;
            }
        }
        System.out.println(res + 1);

    }
}
