package com.striver79.binary_serach;

//https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
public class AggressiveCows {

    static int[] STALLS = {0, 3, 4, 7, 10, 9};

    static int K = 4;

    public static void main(String[] args) {
        minOfMaxPossibledistances();
    }

    private static void minOfMaxPossibledistances() {

        int left = 1;
        int right = STALLS[STALLS.length-1]-STALLS[0];
        int res = Integer.MIN_VALUE;

        while (left<=right){
            int mid = (left+right)/2;
            if(isPossible(mid)){
                res = Math.max(mid,res);
                left=mid+1;
            } else {
                right=mid-1;
            }
        }
        System.out.println(res);
    }

    private static boolean isPossible(int dis) {
        int lastVal = STALLS[0];
        int cntCows = 1;
        for(int i=1; i<STALLS.length; i++){
            if(STALLS[i]-lastVal >= dis){
                cntCows++;
                lastVal= STALLS[i];
            }
            if(cntCows >= K) return true;
        }
        return false;

    }
}
