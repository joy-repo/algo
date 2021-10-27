package com.arrays;

import java.util.HashMap;
import java.util.Map;

public class SubarrayHavingGivenSum {

    static int[] arr = {1, 4, 20, 3, 10, 5};
    static public int SUM = 33;

    public static void main(String[] args) {
        sol();

    }

    public static void sol() {

        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            map.put(sum, i);

            if (sum == SUM)
                cnt++;

            if (sum > SUM && map.containsKey(sum - SUM))
                cnt++;
        }
        System.out.println(cnt);

    }

}
