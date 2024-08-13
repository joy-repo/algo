package com.striver79.arrays_hashing;

///https://takeuforward.org/arrays/count-subarray-sum-equals-k/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumberOfSubarraysWithSumN {

    static int ARR[] = {3, 1, 2, 4};
    static int SUM = 6;
// 3 ->0
// 4 -> 1
// 6 -> 2
// 10 -> 3
    public static void main(String[] args) {
        solution();
    }

    private static void solution() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum = 0;
        int res =0;

        for(int i = 0; i < ARR.length; i++){
            sum=sum+ARR[i];
            if(sum <= SUM){
                map.putIfAbsent(sum, new ArrayList<>());
                map.get(sum).add(i);
                if(sum==SUM) res++;
                continue;
            }
            res = res + map.getOrDefault(sum-SUM, Collections.EMPTY_LIST).size();
        }
        System.out.println(res);


    }
}
