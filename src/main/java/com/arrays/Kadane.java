package com.arrays;

public class Kadane {

    static int[] ARR = {-2, -3, 4, -1, -2, 1, 5, -3};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int sum = 0;
        int res = 0;
        int start = 0;
        int end = 0;

        for (int i = 0; i < ARR.length; i++) {
            sum = sum + ARR[i];
            if (sum < 0) {
                sum = 0;
                start = i + 1;
            }
            if (sum > res) {
                res = sum;
                end = i;
            }
        }
        System.out.println(res);
        System.out.println(start);
        System.out.println(end);

    }


}
