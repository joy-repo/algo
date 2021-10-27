package com.arrays;

public class IndexOfZeroToBeReplacedForMaximum1s {

    static int[] ARR = {0, 0, 1, 0, 1, 1, 1, 0, 1, 1};

    public static void main(String[] args) {
        findIndex();
    }

    private static void findIndex() {

        int prevSum = 0;
        int currSum = 0;
        int prev0Position = 0;
        int maxSum = 0;
        int maxSumPosition = 0;

        for (int i = 0; i < ARR.length; i++) {
            if (ARR[i] == 1) {
                currSum++;
            }
            if (ARR[i] == 0) {
                if (maxSum < prevSum + currSum) {
                    maxSumPosition = prev0Position;
                    maxSum = prevSum + currSum;
                }
                prevSum = currSum;
                currSum = 0;
                prev0Position = i;

            }
        }
        if (maxSum < prevSum + currSum) {
            maxSumPosition = prev0Position;
            maxSum = prevSum + currSum;
        }

        System.out.println(maxSumPosition);
        System.out.println(maxSum + 1);

    }
}
