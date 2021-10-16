package com.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIS {

    // static int[] ARR = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
    static int[] ARR = {1, 2, 0, 3, 8};

    public static void main(String[] args) {
        System.out.println(sol_RECC(0, Integer.MIN_VALUE));
        List<Integer> res = sol_RECC_Return(0, Integer.MIN_VALUE);
        System.out.println(res);
        sol_DP();

        printAllLIS_Recc(0, Integer.MIN_VALUE, "");

    }

    private static void sol_DP() {
        int[] DP = new int[ARR.length];
        Arrays.fill(DP, 1);
        int[] tracker = new int[ARR.length];
        Arrays.fill(tracker, -1);


        for (int j = 1; j < ARR.length; j++) {
            for (int i = 0; i < j; i++) {
                if (ARR[i] < ARR[j]) {
                    if (DP[j] < DP[i] + 1) {
                        DP[j] = DP[i] + 1;
                        tracker[j] = i;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(DP));
        System.out.println(Arrays.toString(tracker));

    }

    private static void printAllLIS_Recc(int i, int val, String res) {

        if (i == ARR.length - 1) System.out.println(res);

        for (int n = i + 1; n < ARR.length; n++) {
            if (val < ARR[n])
                printAllLIS_Recc(n, ARR[n], res + "," + val);
            else
                printAllLIS_Recc(n, val, res);

        }
    }

    private static List<Integer> sol_RECC_Return(int i, int val) {

//        if (i == ARR.length-1) {
//            List<Integer> ll = new ArrayList<>();
//          //  if (val < ARR[i]) ll.add(ARR[i]);
//            return ll;
//        }
        List<Integer> res = new ArrayList<>();

        for (int n = i + 1; n < ARR.length; n++) {

            if (val < ARR[n]) {
                List<Integer> llT = sol_RECC_Return(n, ARR[n]);
                if (val != Integer.MIN_VALUE) llT.add(val);
                if (res.size() < llT.size())
                    res = llT;

            } else {
                List<Integer> llT1 = sol_RECC_Return(n, val);
                if (res.size() < llT1.size())
                    res = llT1;
            }
        }
        // res.add(val);
        return res;

    }


    private static int sol_RECC(int i, int val) {

        if (i == ARR.length) return 1;
        int res = 0;
        for (int n = i + 1; n < ARR.length; n++) {

            if (val < ARR[n])
                res = Math.max(sol_RECC(n, ARR[n]) + 1, res);
            else
                res = Math.max(sol_RECC(n, val), res);
        }
        return res;

    }


    //###################################################################

//    public int longestSubsequenceRecursive(int arr[]){
//        int maxLen = 0;
//        for(int i=0; i < arr.length-1; i++){
//            int len = longestSubsequenceRecursive(arr,i+1,arr[i]);
//            if(len > maxLen){
//                maxLen = len;
//            }
//        }
//        return maxLen + 1;
//    }
//
//    private int longestSubsequenceRecursive(int arr[], int pos, int lastNum){
//        if(pos == arr.length){
//            return 0;
//        }
//        int t1 = 0;
//        if(arr[pos] > lastNum){
//            t1 = 1 + longestSubsequenceRecursive(arr, pos+1, arr[pos]);
//        }
//        int t2 = longestSubsequenceRecursive(arr, pos+1, lastNum);
//        return Math.max(t1, t2);
//    }
//
//    public static void main(String args[]){
//        LIS lis = new LIS();
//        int arr[] = {23,10,22,5,33,8,9,21,50,41,60,80,99, 22,23,24,25,26,27};
//        //int result = lis.longestSubsequenceWithActualSolution(arr);
//        int result1 = lis.longestSubsequenceRecursive(arr);
//      //  System.out.println(result);
//        System.out.println(result1);
//    }


}
