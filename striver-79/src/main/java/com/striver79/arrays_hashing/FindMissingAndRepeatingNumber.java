package com.striver79.arrays_hashing;


//https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/

// S1 = n(n+1)/2
// S2 = n(n+1)(2n+1)/6

import java.util.Arrays;

//
public class FindMissingAndRepeatingNumber {

    static int[] ARR = {3,1,2,5,3};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int s1_e = 0;
        int s2_e = 0;


        for( int c : ARR)
            s1_e+=c;

        for( int c : ARR)
            s1_e+=c*c;

        int n = ARR.length;

        int s1 = (n*(n+1))/2;
        int s2 = (n*(n+1)*(2*n+1))/6;




    }
}
