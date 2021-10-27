package com.arrays;

public class LargestRectangularAreaHistogram {

    static int hist[] = {6, 2, 5, 4, 5, 1, 6};

    public static void main(String[] args) {
        sol_bruteForce(); //n^2
        sol();
    }

    private static void sol() {

        int dp[] = new int[hist.length];

    }


    private static void sol_bruteForce() {

        int max = 0;
        int temp = 0;

        for (int i = 0; i < hist.length - 1; i++) {
            int min = hist[i];
            for (int j = i + 1; j < hist.length; j++) {
                min = Math.min(min, hist[j]);
                temp = Math.max(temp, min * (j - i + 1));
            }
            max = Math.max(temp, max);
        }
        System.out.println(max);
    }
}