package com.arrays;


//Find maximum sum of subsequence with no adjacent elements

public class MaximumSumNoAdjacentElemnts {


    public static int[] arr = {1, 2, 9, 4, 5, 0, 4, 11, 6};

    public static void main(String[] args) {
        sol();
        int res = sol_rec(0,0);
        System.out.println(res);
        System.out.println();
     //    sol_rec_1(0, 0);
    }

//    private static void sol_rec_1(int i, int sum) {
//
//        if(i==arr.length) {System.out.print(sum+","); return;}
//
//        sol_rec_1(i+1,sum );
//        sol_rec_1(i+2,sum+arr[i]);
//
//    }

    private static int sol_rec(int i, int prev) {

        if (i == arr.length) return 0;

        int incl = sol_rec(i + 1, prev);
        int excl = 0;
        if (prev + 1 != i)
            excl = sol_rec(i + 1, i) + arr[i];

        return Math.max(incl, excl);

    }

    private static void sol() {

        int max1 = 0;
        int max = 0;

        int res = 0;

        for (int n : arr) {
            max1 = Math.max(max1 + n, max1);
            int temp = Math.max(max, max1);
            max1 = max;
            max = temp;
            res = Math.max(res, max);

        }

        System.out.println(res);

    }
}
