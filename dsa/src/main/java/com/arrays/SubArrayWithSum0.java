package com.arrays;

//Check if subarray with a sum 0 is exists or not
//Check if subarray with a sum  is exists or not
//Print all the sub arrays

import java.util.*;

public class SubArrayWithSum0 {

    static int[] arr = {3, 4, -7, 1, 3, 3, 1, -4};
    static int sum = 7;


    public static void main(String[] args) {

        checkIfSumExistsOnlyIfSumIs0();
        // checkIfSumExists();
        // printAllSubArraysWithSum();

    }

    private static void checkIfSumExists() {

        Map<Integer, Integer> map = new HashMap<>();
        int sumT = 0;
        for (int i = 0; i < arr.length; i++) {
            sumT = sumT + arr[i];
            if (sumT == sum) {
                System.out.println("true");
                return;
            }
            if (map.containsKey(sumT - sum)) {
                System.out.println("true");
                return;
            }
            map.putIfAbsent(sumT, i);
        }
        System.out.println("false");
    }

    private static void printAllSubArraysWithSum() {

        Map<Integer, List<Integer>> sumMap = new HashMap<>();
        int sumT = 0;
        for (int i = 0; i < arr.length; i++) {
            sumT = sumT + arr[i];

            if (sum == sumT) {
                System.out.println("printSubArrFromaPos(0," + i + ")");
                printSubArrFromaPos(0, i);
            }
            if (sumMap.containsKey(sumT - sum)) {
                System.out.println("printSubArr(" + sumMap.get(sumT - sum).toString() + "," + i + ")");
                printSubArr(sumMap.get(sumT - sum), i);

            }
            sumMap.putIfAbsent(sumT, new ArrayList<>());
            sumMap.get(sumT).add(i);

        }

    }

    private static void printSubArr(List<Integer> integers, int i) {
        for (int a : integers) {
            printSubArrFromaPos(a + 1, i);
            System.out.println("-----------------");
        }
    }

    private static void printSubArrFromaPos(int strt, int end) {
        for (int n = strt; n <= end; n++)
            System.out.print(arr[n] + ",");
        System.out.println();
    }

    private static void checkIfSumExistsOnlyIfSumIs0() {
        Set<Integer> sumStore = new HashSet<Integer>();
        sumStore.add(0);
        int sumT = 0;

        for (int i : arr) {
            sumT = sumT + i;
            if (sumStore.contains(sum)) {
                System.out.println("true");
                return;
            }
        }
        System.out.println("false");

    }


}
