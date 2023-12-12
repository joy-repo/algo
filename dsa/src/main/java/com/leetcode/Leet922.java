package com.leetcode;

import java.util.Arrays;

public class Leet922 {

    static int[] nums = {4, 2, 5, 7};

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {
        int i = 1;
        int j = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[i] % 2 == 0) {
                while ( nums[j] % 2 != 1)
                    j = j + 2;

                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
            i = i + 2;

        }
        System.out.println(Arrays.toString(nums));
    }
}
