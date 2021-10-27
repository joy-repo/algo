package com.arrays;

public class DistinctCombinationsOfGivenLength {

    static int[] arr = {1, 2, 3, 4, 5};
    static int K = 5;

    public static void main(String[] args) {
        solRec(0, 0, "");
    }


    public static void solRec(int i, int j, String s) {

        if (j > arr.length)
            return;

        if (s.length() == K) {
            System.out.println(s);
            return;
        }

        for (int n = i; n < arr.length; n++)
            solRec(n + 1, j + 1, s + arr[n]);

    }


}
