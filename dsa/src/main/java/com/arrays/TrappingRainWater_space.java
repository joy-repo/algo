package com.arrays;


//Trapping Rain Water within given set of bars
public class TrappingRainWater_space {

    public static int[] iArr = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

    public static void main(String[] args) {
        sol();
        sol2();
    }

    private static void sol2() {


    }

    public static void sol() {

        int[] maxRight = new int[iArr.length];
        int[] maxLeft = new int[iArr.length];

        maxLeft[0] = iArr[0];
        maxRight[iArr.length - 1] = iArr[iArr.length - 1];

        for (int i = 1; i < iArr.length; i++)
            maxLeft[i] = Math.max(maxLeft[i - 1], iArr[i]);

        for (int i = iArr.length - 2; i >= 0; i--)
            maxRight[i] = Math.max(maxRight[i + 1], iArr[i]);

        int vol = 0;

        for (int i = 0; i < iArr.length; i++)
            vol += (iArr[i] < Math.min(maxRight[i], maxLeft[i]) ? Math.min(maxRight[i], maxLeft[i]) - iArr[i] : 0);
        System.out.println(vol);

    }

}
