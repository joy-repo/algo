package com.practice.dp;

public class MaxProductCutting_CopySolution {


    // The main function that returns
    // maximum product obtainable from
    // a rope of length n
    static int maxProd(int n) {
        // Base cases
        if (n == 0 || n == 1) return 0;

        // Make a cut at different places
        // and take the maximum of all
        int max_val = 0;
        for (int i = 1; i < n; i++)
            max_val = Math.max(max_val,
                    Math.max(i * (n - i),
                            maxProd(n - i) * i));

        // Return the maximum of all values
        return max_val;
    }

    /* Driver program to test above functions */
    public static void main(String[] args) {
        System.out.println("Maximum Product is "
                + maxProd(17));
    }
}
