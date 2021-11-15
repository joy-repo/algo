package com.backtracking;

import java.util.ArrayList;
import java.util.List;

public class AllSubSquencesWithKSum {
    static int arr[] = {5, 12, 3, 17, 1,
            18, 15, 3, 17};
    static int K = 15;

    public static void main(String[] args) {
        List<List<Integer>> res = new ArrayList<>();
        solBYRecc(0, 0, res, new ArrayList<>());
        res.stream().forEach(System.out::println);
    }

    private static void solBYRecc(int i, int sum, List<List<Integer>> res, List<Integer> list) {

        if (sum == K) {
            System.out.println(list);
            res.add(new ArrayList<>(list));
        }

        if (i == arr.length) return;


        for (int c = i; c < arr.length; c++) {
            list.add(arr[c]);
            solBYRecc(c + 1, sum + arr[c], res, list);
            list.remove(list.indexOf(arr[c]));
        }
    }
}
