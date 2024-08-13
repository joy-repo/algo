package com.striver79.arrays_hashing;

//https://takeuforward.org/data-structure/count-inversions-in-an-array/

import java.util.Arrays;

public class CountInversions {

    static int[] ARR = { 1, 20, 6, 4, 5 };
    static int RES=0;

    public static void main(String[] args) {
        int[] sorted = mergeSort(0, ARR.length-1);
        Arrays.stream(sorted).forEach(System.out::println);
        countInversions();
        System.out.println("RES : " +RES);
    }

    private static void countInversions() {

    }

    private static int[] mergeSort(int l, int r) {

        if(l==r)
            return new int[]{ARR[l]};

        int mid = (l+r)/2;
        int[] lArr = mergeSort(l, mid);
        int[] rArr = mergeSort(mid+1, r);
      // calInversions(lArr, rArr);
        return merge(lArr, rArr);

    }

    private static void calInversions(int[] lArr, int[] rArr) {

        int lc = 0;


        while (lc < lArr.length){
            if(lArr[lc] > rArr[0]){
                RES = RES + lArr.length-lc;
                return;
            }
            lc++;
        }

    }


    private static int[] merge(int[] lArr, int[] rArr) {

        int lc = 0;
        int rc = 0;
        int[] res = new int[lArr.length+ rArr.length];
        int c=0;
        while (lc< lArr.length && rc < rArr.length){

            if(lArr[lc]<rArr[rc]){
                res[c]=lArr[lc];
                lc++;
                c++;
            } else {
                res[c]=rArr[rc];
                rc++;
                c++;
                RES = RES+lArr.length-lc;
            }

        }

        while (lc< lArr.length){
            res[c]=lArr[lc];
            lc++;
            c++;
        }

        while (rc < rArr.length){
            res[c]=rArr[rc];
            rc++;
            c++;
        }
        return res;
    }


}
