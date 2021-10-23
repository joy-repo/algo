package com.practice.interview;

//https://www.youtube.com/watch?v=ekh3rlCS-4o&list=PLBtMh4xfa9FHU2hNv3cwiZY649x3Hd8rR&index=2
//Quetn2

public class IntegerArray3ElementsMaxProduct {

    static int[] arr = {10, -1, 3, 5, 6, -100};
    static int K = 3;

//    int max1 = 10;
//    int max2 = 6;
//    int max3 = 5;
//
//    int min1 = -100;
//    int min2 = -1;

    public static void main(String[] args) {
        int res = sol();
        System.out.println(res);
    }

    private static int sol() {

        int max1 = 0;
        int max2 = 0;
        int max3 = 0;

        int min1 = 0;
        int min2 = 0;

        for (int n : arr) {

            if (max1 < n) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 < n) {
                max3=max2;
                max2 = n;
            } else if (max3 < n) {
                max3 = n;
            }
            if (min1 > n) {
                min2 = min1;
                min1 = n;
            } else if (min2 > n) {
                min2 = n;
            }
        }
        System.out.println("max1:" + max1);
        System.out.println("max2:" + max2);
        System.out.println("max3:" + max3);
        System.out.println("min1:" + min1);
        System.out.println("min2:" + min2);
        int prod1 = max1 * max2 * max3;
        int prod2 = min1 * min2 * max1;

        return prod1 > prod2 ? prod1 : prod2;

    }


}


