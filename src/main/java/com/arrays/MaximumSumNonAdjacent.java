package com.arrays;


//Find maximum sum of subsequence with no adjacent elements
public class MaximumSumNonAdjacent {

    static int arr[] = {5, 5, 10, 100, 10, 5};

    public static void main(String[] args) {
        System.out.println(solRecc(0, false));
        SolDP();
    }

    public static int solRecc(int i, boolean isAdjacent) {

        if (i == arr.length)
            return 0;

        if (!isAdjacent)
            return Math.max(solRecc(i + 1, true) + arr[i], solRecc(i + 1, false));

        return solRecc(i + 1, false);

    }

    public static void SolDP() {
        int max_incl = arr[0];
        int max_excl = 0;
        int max = 0;

        for (int i = 1; i < arr.length; i++) {

            max_excl = Math.max(max_excl + arr[i], max_excl);
            int t = max_excl;
            max_excl = max_incl;
            max_incl = t;
            max = Math.max(Math.max(max, max_excl), max_incl);
        }
        System.out.println(max);
    }

}
