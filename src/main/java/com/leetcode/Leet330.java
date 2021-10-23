package com.leetcode;

import java.util.Arrays;

public class Leet330 {

    public static void main(String[] args) {
        int[] nums = {1,2};

        int n = 8;
        int k = 0;
        //minPatches(nums,n);
        k = minPatches_DP(nums, n);
        System.out.println(k);
    }


    public static int minPatches_DP(int[] nums, int n) {

        int initLen = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int j = 1;

        while (!allTrue(dp)) {



            for (int i = 0; i < nums.length; i++) {

                boolean[] temp = Arrays.copyOf(dp, dp.length);
                for (j = 1; j <= n; j++) {
                    if (nums[i] <= j)
                        temp[j] = dp[j - nums[i]];
                }
                dp = temp;
            }
            ///find first false
            int c = 1;
            for (c = 1; c <= n; c++) {
                if (!dp[c]) break;
            }
            if (allTrue(dp)) break;
            nums=addCToNums(nums, c);
            System.out.println("----------");
            System.out.println(Arrays.toString(nums));
            System.out.println(Arrays.toString(dp));
            System.out.println("----------");
        }
        System.out.println("----------");
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(nums));
        System.out.println("----------");
        return nums.length-initLen;

    }

    private static int[] addCToNums(int[] nums, int c) {
        int newNums[] = new int[nums.length + 1];
        int i = 0;
        for (i = 0; i < nums.length; i++)
            if (nums[i] < c)
                newNums[i] = nums[i];
            else break;

        newNums[i] = c;
        for (int j = i + 1; j < newNums.length; j++)
            newNums[j] = nums[j - 1];
        return newNums;
    }

    private static boolean allTrue(boolean[] dp) {
        for (boolean b : dp) {
            if (!b) return false;
        }
        return true;
    }


///////////////////////////////////////////////////////////////////////



    public static int minPatches(int[] nums, int n) {

        int initLen = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int j = 1;

        for (int i = 0; i < nums.length; i++) {
            boolean[] temp = new boolean[n + 1];
            int t = 0;
            ///copy dp to temp -->o(n)
            for (int c = 0; c <= j; c++) {
                temp[c] = true;
            }
            ///c---> o(n)
            dp = temp;
            boolean addN = false;
            while (j <= n) {
                if (nums[i] >= j) temp[j] = dp[j - nums[i]];
                if (temp[j] && !temp[j - 1]) {
                    addN = true;
                    break;
                }
                j++;
            }

            // nums =createNewAndInsert(nums, j-1);
            if (addN) {
                int[] nn = new int[nums.length + 1];
                System.arraycopy(nn, 0, nums, 0, i - 1);
                nn[i] = j - 1;
                System.arraycopy(nn, i + 1, nums, i, nums.length - 1 - i);
                nums = nn;
                i--;
                j--;
            } else {
                j = 1;
            }
        }

        return nums.length - initLen;

    }

//    private int[] createNewAndInsert(int[] nums, int n) {
//
//        int[] nn = new int[nums.length+1];
//        int i=0;
//        for( i =0; i<nums.length; i++){
//            if(n<nums[i]) nn[i]=nums[i];
//        }
//        nn[i]=n;
//        for( int j =i+1; i<nn.length; i++){
//             nn[j]=nums[j-1];
//        }
//        return  nn;
//
//        System.arraycopy(nn, 0, );
//
//    }
}
