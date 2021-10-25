package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leet90 {

     static int[] nums = {1,2,2};

    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        sol(0, new ArrayList<Integer>(), res);
        res.stream().forEach(System.out::println);

    }

    private static void sol(int i, List<Integer> tmp, List<List<Integer>> res) {

        res.add(new ArrayList<>(tmp));
        if(i== nums.length) return;

        for(int n=i; n<nums.length;n++){
            if(n!=i && nums[n]==nums[n-1]) continue;
            tmp.add(nums[n]);
            sol(n+1, tmp,res);
            tmp.remove(tmp.lastIndexOf(nums[n]));
        }
    }
}
