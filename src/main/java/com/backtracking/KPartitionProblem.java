package com.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KPartitionProblem {

    static int[] S = {7, 3, 5, 12, 2, 1, 5, 3, 8, 4, 6, 4};

    // total number of items in `S`

    static int k = 5;

    public static void main(String[] args) {
        int n = Arrays.stream(S).sum() / k;
        List<List<Integer>> ll = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            ll.add(new ArrayList<>());
        }

        sol(ll, 0, n);

    }

    private static boolean sol(List<List<Integer>> ll, int i, int n) {

        if (i == S.length) {
            if (solved(ll, n)) {
                ll.forEach(System.out::println);
                return true;
            }
            return false;
        }
        for (List<Integer> ls : ll) {
            if (ls.stream().mapToInt(m -> m).sum() + S[i] <= n) {
                ls.add(S[i]);
                if (sol(ll, i + 1, n)) return true;
                ls.remove(ls.size() - 1);
            }
        }
        return false;

    }

    private static boolean solved(List<List<Integer>> ll, int n) {

        for (List<Integer> ls : ll) {
            if (n != ls.stream().mapToInt(k -> k).sum()) return false;
        }
        return true;

    }

}
