package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Leet330 {

    public static void main(String[] args) {
        int[] nums = {1,5,7};

        int n = 18;
        int k = 0;
        //minPatches(nums,n);
        k = minPatches_DP(nums, n);

        sol(nums,n);
    }


    static void sol(int[] nums, int n) {
        int res =0;
        int len = nums.length;
      //  List<Integer> list = new LinkedList<>(Arrays.stream(nums).boxed().collect(Collectors.toList()));
        int sum=nums[0];
        int j=0;
        int r =0;
        boolean endOfArray = false;
        while(r<=n || j<nums.length){
            if(r>=n) break;
            if(endOfArray){
                if(j== nums.length-1){
                    r=r+nums[j];
                    j++;
                    continue;
                }
                r=2*r+1;
                res++;
                continue;
            }
            if(r==sum ){
                j=j+1;
                sum=sum+nums[j];
                if(j==nums.length-1) endOfArray=true;
               // r++;
            }
            if(r<sum){
                if(2*r+1<r+nums[j]){
                    r=2*r+1;
                    res++;
                }else {
                    r=r+nums[j];
                    j++;
                    if(j==nums.length-1) endOfArray=true;
                }

            }
            if(r>sum){
              r=r+nums[j];
              j++;
              sum=sum+nums[j];
              if(j==nums.length-1) endOfArray=true;
            }
        }
        System.out.println("joy---" +res);

    }

    public static int minPatches_DP(int[] nums, int n) {

        int initLen = nums.length;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        int j = 1;

        while (!allTrue(dp)) {

            dp = new boolean[n + 1];
            dp[0] = true;

            for (int i = 0; i < nums.length; i++) {

                boolean[] temp = Arrays.copyOf(dp, dp.length);
                for (j = 1; j <= n; j++) {
                    if (nums[i] <= j)
                        temp[j] = dp[j - nums[i]];

                }
                dp = temp;
                System.out.println("i=" + i + ",j=" + j);
                System.out.println(Arrays.toString(dp));
                System.out.println(Arrays.toString(nums));
                System.out.println("----------");

            }
            ///find first false
            int c = 1;
            for (c = 1; c <= n; c++) {
                if (!dp[c]) break;
            }
            if (allTrue(dp)) break;
            nums = addCToNums(nums, c);
//            System.out.println("----------");
//            System.out.println(Arrays.toString(nums));
//            System.out.println(Arrays.toString(dp));
//            System.out.println("----------");
        }
//        System.out.println("----------");
//        System.out.println(Arrays.toString(dp));
//        System.out.println(Arrays.toString(nums));
//        System.out.println("----------");
        return nums.length - initLen;

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


//    public static int minPatches(int[] nums, int n) {
//
//        int initLen = nums.length;
//        boolean[] dp = new boolean[n + 1];
//        dp[0] = true;
//        int j = 1;
//
//        for (int i = 0; i < nums.length; i++) {
//            boolean[] temp = new boolean[n + 1];
//            int t = 0;
//            ///copy dp to temp -->o(n)
//            for (int c = 0; c <= j; c++) {
//                temp[c] = true;
//            }
//            ///c---> o(n)
//            dp = temp;
//            boolean addN = false;
//            while (j <= n) {
//                if (nums[i] >= j) temp[j] = dp[j - nums[i]];
//                if (temp[j] && !temp[j - 1]) {
//                    addN = true;
//                    break;
//                }
//                j++;
//            }
//
//            // nums =createNewAndInsert(nums, j-1);
//            if (addN) {
//                int[] nn = new int[nums.length + 1];
//                System.arraycopy(nn, 0, nums, 0, i - 1);
//                nn[i] = j - 1;
//                System.arraycopy(nn, i + 1, nums, i, nums.length - 1 - i);
//                nums = nn;
//                i--;
//                j--;
//            } else {
//                j = 1;
//            }
//        }
//
//        return nums.length - initLen;
//
//    }

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
