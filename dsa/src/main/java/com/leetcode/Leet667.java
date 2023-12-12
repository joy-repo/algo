package com.leetcode;

import java.util.Arrays;

public class Leet667 {

    public static void main(String[] args) {
        constructArray(6,4);
    }

    public static int[] constructArray(int n, int k) {

        int[] res = new int[n];
        int nochange = n-k;

        for(int i=1; i<=nochange;i++){
            res[i-1]=i;
        }

        boolean reverse =true;
        int strt = nochange+1;
        int end =n;
        for(int i=nochange+1; i<=n; i++ ){
            if(reverse){
                res[i-1]=end;
                end--;
                reverse=false;
            } else{
                res[i-1]=strt;
                strt++;
                reverse=true;
            }
        }
        System.out.println(Arrays.toString(res));
        return res;

    }
}
