package com.arrays;

//Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
public class IndexReplacement {

    static int arr[] = {0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1};

    public static void main(String[] args) {
        /*
         * System.out.println(replacement(arr)); System.out.println(arr.length);
         */
        replacement_(arr);

    }

    static int replacement(int[] arr) {

        int max1Cnt = 0;
        int index = 0;
        int max1Cnt_l_Temp = 0;
        int max1Cnt_r_Temp = 0;
        int zeroIndex = -1;
        boolean zeroCount_l = true;
        int count = -1;
        for (int i : arr) {
            count++;
            if (i == 0) {

                if (!zeroCount_l) {
                    if (max1Cnt < max1Cnt_l_Temp + max1Cnt_r_Temp) {
                        max1Cnt = max1Cnt_l_Temp + max1Cnt_r_Temp;
                        index = zeroIndex;
                    }
                    max1Cnt_l_Temp = max1Cnt_r_Temp;
                    max1Cnt_r_Temp = 0;
                }
                zeroCount_l = !zeroCount_l;
            }
            if (i == 1)
                if (zeroCount_l)
                    max1Cnt_l_Temp++;
                else
                    max1Cnt_r_Temp++;
            zeroIndex = count;
        }

        if (max1Cnt < max1Cnt_l_Temp + max1Cnt_r_Temp) {
            max1Cnt = max1Cnt_l_Temp + max1Cnt_r_Temp;
            index = zeroIndex;
        }
        System.out.println("MaxCount :" + max1Cnt);
        return index;
    }

    static void replacement_(int[] arr) {

        int count1s_left = 0;
        int count1s_right = 0;
        int maxCount = 0;
        int index = 0;
        int indexMaxCount = 0;
        boolean lRight = true;

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == 1)
                if (lRight)
                    count1s_right++;
                else
                    count1s_left++;
            if (arr[i] == 0) {
                indexMaxCount = (count1s_left + count1s_right) > maxCount ? index : indexMaxCount;
                maxCount = Math.max(count1s_left + count1s_right, maxCount);
                count1s_left = count1s_right;
                count1s_right = 0;
                lRight = !lRight;
                index = i;
            }
            if (arr[i] == 1 && i == arr.length - 1) {
                indexMaxCount = (count1s_left + count1s_right) > maxCount ? index : indexMaxCount;
                maxCount = Math.max(count1s_left + count1s_right, maxCount);
                count1s_right = count1s_left;
                lRight = !lRight;
            }

        }

        System.out.println(maxCount);
        System.out.println(indexMaxCount);

    }
}
