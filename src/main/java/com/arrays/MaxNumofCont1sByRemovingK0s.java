package com.arrays;

//Find the maximum sequence of continuous 1’s that can be formed by replacing at-most k zeroes by ones

import java.util.ArrayList;
import java.util.List;

public class MaxNumofCont1sByRemovingK0s {

    static int[] Arr= { 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0 };
    static int K=2;

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        List<Integer> ll = new ArrayList<>();
        int maxCount=0;
        int currCount=0;
        int totalCurrCount=0;



        for( int i=0;i<Arr.length;i++){

            if(Arr[i]==1){
                currCount++;
                continue;
            }
            if(Arr[i]==0 && ll.size()<K){
                ll.add(currCount);
                totalCurrCount=totalCurrCount+currCount+1;
                maxCount=Math.max(maxCount,totalCurrCount);
                currCount=0;
            }
            if(Arr[i]==0 && ll.size()>=K){
                Integer rem =ll.remove(0);
                totalCurrCount=totalCurrCount-rem+currCount;
                maxCount=Math.max(maxCount,totalCurrCount);
                ll.add(currCount);
                currCount=0;

            }


        }
        totalCurrCount=totalCurrCount+currCount;
        maxCount=Math.max(maxCount,totalCurrCount);
        System.out.println(maxCount);

    }
}
