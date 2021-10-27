package com.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindSumSubSets {

    static int res = 0;
    static Set<List<Integer>> rs = new HashSet<>();

    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 4, 5, 6, 7};

        //System.out.println(findSubsetSum(arr, 0, sum, 0, new ArrayList<>(), 0));
        //System.out.println(rs);

        test(arr, 0, new ArrayList<Integer>());
    }

    static int findSubsetSum(int[] arr, int n, int sum, int tmpSum, List<Integer> li, int res) {
        System.out.println("logs------>" + li);
        if (n == arr.length) {
            if (sum == tmpSum) {
                rs.add(new ArrayList<>(li));
                System.out.println(li);
                res++;

            }
            return res;
        }

        if (sum == tmpSum) {
            rs.add(new ArrayList<>(li));
            System.out.println(li);
            res++;
            return res;
        }
        li.add(arr[n]);
        res = findSubsetSum(arr, n + 1, sum, tmpSum + arr[n], li, res);
        li.remove(li.size() - 1);
        return findSubsetSum(arr, n + 1, sum, tmpSum, li, res);

    }


    private static void test(int[] arr, int n, List<Integer> li) {
        System.out.println(li);
        if (n == arr.length) return;

        li.add(arr[n]);
        test(arr, n + 1, li);
        li.remove(li.size() - 1);
        test(arr, n + 1, li);
    }

}
