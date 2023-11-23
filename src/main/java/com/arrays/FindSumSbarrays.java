
package com.arrays;

import java.util.HashMap;
import java.util.Map;

public class FindSumSbarrays {

    // Print all subarrays with suma s n

    static int findSubarraySum(int arr[], int n, int sum) {
        // HashMap to store number of subarrays
        // starting from index zero having
        // particular value of sum.
        Map<Integer, Integer> prevSum = new HashMap<>();

        int res = 0;

        // Sum of elements so far.
        int currsum = 0;

        for (int i = 0; i < n; i++) {

            currsum = arr[i] + currsum;

            if (currsum == sum)
                res++;

            if (prevSum.containsKey(currsum - sum))
                res = res + prevSum.get(currsum - sum);

            prevSum.putIfAbsent(currsum, 0);
            prevSum.put(currsum, prevSum.get(currsum) + 1);

        }

        return res;
    }

    public static void main(String[] args) {

//        static int[] ARR = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
//        static int SUM = 0;

        int arr[] = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
        int sum = 0;
        int n = arr.length;
        System.out.println(findSubarraySum(arr, n, sum));

    }

}
