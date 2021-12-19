package com.practice.tushar.dynamic;

//Numbers WIthout Consecutive 1s in binary representation
//https://www.youtube.com/watch?v=a9-NtLIs1Kk&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr&index=38


//3
//001
//010
//011
//100
//101
//111


//4
//0 001
//0 010
//0 011
//0 100
//0 101
//0 111

//1 001
//1 010
//1 011
//1 100
//1 101
//1 111


import java.util.Arrays;

public class Prb38 {

    static  int N = 6;

    public static void main(String[] args) {
        sol();
    }

    private static void sol() {

        int [] DP = new int[N+1];

        DP[0]=1;
        DP[1]=2;
        DP[2]=3;

        for(int i =3; i<=N; i++)
            DP[i]=DP[i-1]+DP[i-2];

        System.out.println(Arrays.toString(DP));

    }
}
