package com.dp;

import java.util.ArrayList;
import java.util.List;

public class MaxProductCutting {

    static int L = 10;
    static int C = 3;

    public static void main(String[] args) {
        int res = solbyRec(L, C, "");
        System.out.println(res);
        // solByDP();
    }

//    private static void solByDP() {
//        ArrayList<Integer>[][] DP = new ArrayList[C][L];
//        DP[0][0] = null;
//        for (int i = 1; i < L; i++) {
//            ArrayList<Integer> ll = new ArrayList<>();
//            int t = (i + 1) / 2;
//            ll.add(t);
//            ll.add(i + 1 - t);
//            DP[0][i] = ll;
//        }
//
//
//        for (int i = 1; i < C; i++)
//            for (int j = 0; j < L; j++)
//                if (j <= i) DP[i][j] = null;
//                else
//                    DP[i][j] = getList(DP[i-1][j]);
//
//
//    }
//
//
//    private static ArrayList<Integer> getList(List<Integer> list) {
//
//        int s =list.size();
//        int i=0;
//        int max=0;
//        List<Integer> resL= null;
//        while (i<s){
//
//            for(int j =0; j<list.size(); j++){
//                if(list.get(j)==1)
//            }
//        }
//
//    }


    private static int solbyRec(int len, int cuts, String str) {

//        if (cuts == 0) {
//            if (len > 0) {
//                System.out.println(str + len);
//                return len;
//            }
//        }
        if (len <= 0) return 0;

        int maxProduct = 1;

        for (int i = 1; i <= len; i++) {
            int temp = solbyRec(len - i, cuts - 1, str + i + ",") * i;
            maxProduct = Math.max(maxProduct, temp);
        }
        return maxProduct;

    }

    private static int solbyBottomUP(int len, int cuts, String str) {

        if (cuts == 0) {
            if (len > 0) {
                System.out.println(str + len);
                return len;
            }
        }
        if (len == 0) return 0;

        int maxProduct = 1;

        for (int i = 1; i <= len; i++) {
            int temp = solbyRec(len - i, cuts - 1, str + i + ",") * i;
            maxProduct = Math.max(maxProduct, temp);
        }
        return maxProduct;

    }
}
