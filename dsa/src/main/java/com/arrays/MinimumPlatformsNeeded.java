package com.arrays;

//Find maximum sum of subsequence with no adjacent elements

public class MinimumPlatformsNeeded {

    public static double[] arrival = {9.00, 9.40, 9.50, 11.00, 15.00, 18.00};
    public static double[] departure = {9.10, 12.00, 11.20, 11.30, 19.00, 20.00};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int trains = 0;
        int platforms = 0;
        int i_a = 0;
        int i_d = 0;

        while (i_a < arrival.length) {


            if (arrival[i_a] < departure[i_d]) {
                trains++;
                i_a++;
            } else {
                trains--;
                i_d++;
            }
            platforms = Math.max(platforms, trains);

        }
        System.out.println(platforms);
    }

}
