package com.striver79.binary_serach;

import java.util.Arrays;

public class KokoEatingBananas {

    static int[] ARR = {7, 15, 6, 3};
    static int H = 8;

    public static void main(String[] args) {
        solution();
      //  System.out.println(calculateH(5));
    }

    private static void solution() {

        int max = Integer.MIN_VALUE;

        for(int i : ARR){
            max = Math.max(max,i);
        }

        int left = 1;
        int right = max;
        int res = Integer.MAX_VALUE;

        while(left<=right){

            int mid = (right+left)/2;
             if(H >= calculateH(mid)){
                 right = mid-1;
             } else {
                 left = mid+1;
             }

        }
        System.out.println(" left "+ left);
        System.out.println(res);

    }

    private static int calculateH(int perDay) {

        int[] arrC = Arrays.copyOf(ARR, ARR.length);
        int h =0;
        boolean isnotEmpty = true;
        int hTemp = 0;
        while(isnotEmpty){

            isnotEmpty = false;
            for(int c=0; c<arrC.length; c++){
                if(arrC[c]>0) {
                    arrC[c] = Math.max(arrC[c] - perDay, 0);
                    hTemp++;
                }
                if(arrC[c]!=0){
                    isnotEmpty = true;
                }
            }

        }
        return hTemp;
    }
}
