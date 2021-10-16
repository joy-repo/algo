package com.dp;

import java.util.Arrays;

public class MaxProductCuttingUnboundedCut {

    static int L = 17;


    public static void main(String[] args) {
        int res = solbyRec(0);
        System.out.println(res);
        int res1 =solbyDP();
        System.out.println(res1);
    }

    private static int solbyRec(int l) {

        if(l==L) return 1;
        int res=0;

        for(int i=l+1; i<=L; i++){
            int temp =solbyRec(i)*(i-l);
            res = Math.max(res,temp);
        }
        return res;
    }

    private static int solbyDP() {


        int res=0;
        int val[] = new int[L+1];
        val[0]=val[1]=1;
        val[2]=1;
        val[3]=2;
        for(int l=1; l<=L; l++) {
            for (int i = 0; i < l; i++) {
                int temp = val[i]*(l-i);
                res = Math.max(res, temp);
            }
            val[l]=res;
        }
        System.out.println(Arrays.toString(val));
        return res;
    }

}
