package com.arrays;


import java.util.*;

public class LIS {

    public static int[] arr = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};

    public static void main(String[] args) {
        System.out.println(arr.length);
        sol();
        sol_DP();
  //      sol_rec();

    }

//    private static int sol_rec(int i) {
//
//        if(i>=arr.length) return 1;
//
//        for(int j=0; j<i;j++){
//            if(arr[j]<arr[i]) return 1+ sol_rec(1);
//        }
//
//    }

    private static void sol_DP() {
        int[] DP = new int[arr.length];
        int[] store = new int[arr.length];
        Arrays.fill(DP,1);
        Arrays.fill(store,-1);
        for(int i=1;i<arr.length;i++ ){
            for(int j=0;j<i;j++){
                if(arr[i]>arr[j] && DP[i]<DP[j]+1) {
                    DP[i]=DP[j]+1;
                    store[i]=j;
                }
            }
        }
        System.out.println();
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(DP));
        System.out.println(Arrays.toString(store));
    }


//    private static void sol_DP() {
//        int[][] DP = new int[arr.length+1][arr.length+1];
//
//        for(int y=1; y< DP.length;y++){
//            for(int x=1; x<DP[0].length; x++){
//                if(arr[y-1]>arr[x-1])
//                    DP[y][x] = Math.max(DP[y-1][x-1]+1, DP[y-1][x]);
//                else
//                    DP[y][x]=Math.max(DP[y-1][x],DP[y][x-1]);
//            }
//        }
//
//    }

    private static void sol() {
        int i = 1;
        Map<Integer, List<Integer>> result = new HashMap<>();
        result.put(1, new ArrayList<>(Arrays.asList(arr[0])));

        for (i = 1; i < arr.length; i++) {

            boolean changed = false;
            for (int n = 1; n <= result.size(); n++) {
                if (result.get(n).get(n - 1) > arr[i]) {
                    result.get(n).remove(n - 1);
                    result.get(n).add(arr[i]);
                    changed = true;
                    break;
                }
            }
            if (!changed)
                copyAndAdd(result, arr[i]);

        }
        System.out.println(result.toString());
    }

    private static void copyAndAdd(Map<Integer, List<Integer>> result, int num) {
        List<Integer> ll = result.get(result.size());

        if (ll.get(result.size() - 1) == num) return;

        result.put(result.size() + 1, new ArrayList<>(ll));
        result.get(result.size()).add(num);
    }


//    private static void storeResult(Map<Integer, List<Integer>> result, List<Integer> temp_store) {
//
//
//        result.putIfAbsent(temp_store.size(), temp_store);
//
//        if (result.get(temp_store.size()).get(temp_store.size() - 1) > temp_store.get(temp_store.size()) - 1) {
//            result.put(temp_store.size(), temp_store);
//        }
//
//    }
}
