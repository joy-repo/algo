package com.arrays;

import java.util.Arrays;

// Maximum profit earned by buying and selling shares any number of times
//Maximum profit earned by buying and selling shares K number of times
public class ProfitByShares {

    public static int[] iArr = {12, 14, 17, 10, 14, 13, 12, 15};
    //public static int[] iArr={ 100, 180, 260, 310, 40, 535, 695 };
    static int K = 5;

    public static void main(String[] args) {
        sol();
        sol1();
        maxProfit(iArr, K);
        System.out.println("--------------------");
        maxProfitSlowSolution(iArr, K);
    }

    // Maximum profit earned by buying and selling shares any number of times
    public static void sol() {

        int profit = 0;
        /* int start = 0; */

        for (int i = 1; i < iArr.length; i++) {

            /*
             * if(iArr[start]<iArr[i] && i == iArr.length-1) profit =
             * profit+iArr[i]-iArr[start];
             */

            if (iArr[i] > iArr[i - 1])
                profit = profit + iArr[i] - iArr[i - 1];

            /*
             * profit = profit + iArr[i-1] - iArr[start]; start = i;
             */
        }

        System.out.println(profit);
    }

    /// when k=1
    public static void sol1() {
        int[] maxArr = new int[iArr.length];
        int len = iArr.length - 1;
        maxArr[len] = iArr[len];

        for (int j = len - 1; j >= 0; j--)
            if (iArr[j] >= maxArr[j + 1])
                maxArr[j] = iArr[j];
            else
                maxArr[j] = maxArr[j + 1];

        int res = 0;
        for (int i = 0; i <= len; i++)
            res = Math.max(res, maxArr[i] - iArr[i]);
        System.out.println(res);

    }

    /// when k=K
    public static int maxProfit(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K + 1][prices.length];

        for (int i = 1; i < T.length; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < T[0].length; j++) {
                T[i][j] = Math.max(T[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, T[i - 1][j] - prices[j]);
            }
        }
        //printActualSolution(T, prices);
        Arrays.stream(T).map(Arrays::toString).forEach(System.out::println);
        return T[K][prices.length - 1];
    }

    /**
     * This is slow method but easier to understand.
     * Time complexity is O(k * number of days ^ 2)
     * T[i][j] = max(T[i][j-1], max(prices[j] - prices[m] + T[i-1][m])) where m is 0...j-1
     */
    public static int maxProfitSlowSolution(int prices[], int K) {
        if (K == 0 || prices.length == 0) {
            return 0;
        }
        int T[][] = new int[K + 1][prices.length];

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, prices[j] - prices[m] + T[i - 1][m]);
                }
                T[i][j] = Math.max(T[i][j - 1], maxVal);
            }
        }
        // printActualSolution(T, prices);
        Arrays.stream(T).map(Arrays::toString).forEach(System.out::println);
        return T[K][prices.length - 1];
    }

}
