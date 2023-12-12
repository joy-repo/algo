package com.divide_conquer;

//https://www.techiedelight.com/search-nearly-sorted-array-ologn-time/

public class SearchInNearlySortedArray {

    static int[] ARR = {1, 2, 5, 4, 7, 6, 8, 9};

    public static void main(String[] args) {
        int res = sol(4);
        System.out.println(res);
    }

    private static int sol(int n) {

        int strt = 0;
        int end = ARR.length - 1;

        while (end >= strt) {
            int mid = (strt + end) / 2;
            if (mid > strt)
                if (ARR[mid - 1] == n) return mid - 1;
            if (mid < end)
                if (ARR[mid + 1] == n) return mid + 1;

            if (ARR[mid] == n) return mid;

            if (n > ARR[mid]) strt = mid + 2;
            if (n < ARR[mid]) end = mid - 2;
        }
        return -1;
    }
}
