package com.divide_conquer;

public class PeakElement {

    static int[] nums = {5, 7, 9, 11, 15};

    public static void main(String[] args) {
        int res = sol();
        System.out.println(res);

    }

    private static int sol() {
        int strt = 0;
        int end = nums.length - 1;
        int diff = (nums[nums.length - 1] - nums[0]) / nums.length;

        while (end >= strt) {
            int mid = (end + strt) / 2;

            if (mid > 0)
                if ((nums[mid] - nums[mid - 1]) > diff)
                    return nums[mid] - diff;

            if (mid < nums.length - 1)
                if ((nums[mid + 1] - nums[mid]) > diff)
                    return nums[mid] + diff;

            if (nums[mid] - nums[0] > mid * diff) {
                end = mid - 1;
            } else {
                strt = mid + 1;
            }

        }

        return -1;

    }
}
