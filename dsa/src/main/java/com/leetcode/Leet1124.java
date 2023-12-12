package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leet1124 {

    public static void main(String[] args) {
        int[] hours = {9,9,6,0,6,6,9};
        int res =longestWPI(hours);
        System.out.println(res);
    }

    public static int longestWPI(int[] hours) {

        Map<Integer,Integer> map=new HashMap<>();
        int sum=0;
        int res=0;

        for(int i=0; i<hours.length; i++){
            sum = sum+(hours[i]>8?1:-1);
            if(sum>0)
                res=Math.max(res,i+1);
            map.putIfAbsent(sum,i);

            if(map.containsKey(sum-1)){
                res=Math.max(res,i-map.get(sum-1));
            }

        }
        return res;
    }
}
