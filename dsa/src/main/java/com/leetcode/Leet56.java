package com.leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Leet56 {

    public static void main(String[] args) {

        //[[1,4],[0,2],[3,5]]
        int[][] intr = {{1,4},
                {0,2},{3,5}};
        merge(intr);
    }


    public static int[][] merge(int[][] intervals) {

        int[][] res = new int[intervals.length][2];
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        int start = intervals[0][0];


        res[0][0]=start;
        int stop= intervals[0][1];
        int j=0;

        for(int i=0; i<intervals.length-1; i++){
            if(stop>=intervals[i+1][0]){
                stop = Math.max(intervals[i+1][1],stop);
                continue;
            } else{
                res[j][0]=start;
                res[j][1]= Math.max(intervals[i][1],stop);
                j++;
                start=intervals[i+1][0];
                stop = intervals[i+1][1];
            }

        }
        res[j][0]=start;
        res[j][1]=Math.max(stop,intervals[intervals.length-1][1]);
        int[][] resize= new int[j+1][2];

        for(int i =0; i<=j;i++){
            resize[i][0]=res[i][0];
            resize[i][1]=res[i][1];
        }
        Arrays.stream(intervals).forEach(a-> System.out.println(Arrays.toString(a)));
        System.out.println("------");
        Arrays.stream(res).forEach(a-> System.out.println(Arrays.toString(a)));
        return resize;
    }
}
