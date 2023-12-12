package com.leetcode;

/*


n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and picks a seat randomly. But after that, the rest of passengers will:

Take their own seat if it is still available,
Pick other seats randomly when they find their seat occupied
What is the probability that the n-th person can get his own seat?
 */

/*
n=3

1/3
1/3 * 1/2

n=4
1/4
1/4*


 */

public class Leet1227 {

    public static void main(String[] args) {

        double res = nthPersonGetsNthSeat(3);
        System.out.println(res);
    }

    public static double nthPersonGetsNthSeat(int n) {
        return n==1?1:0.5;
    }
}
