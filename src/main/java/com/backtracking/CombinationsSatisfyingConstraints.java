package com.backtracking;

//Find all combinations of elements satisfying given constraints

import java.util.Arrays;

public class CombinationsSatisfyingConstraints {

    static int N=3;

    static int[] store = new int[N*2];

    static int reps = 2;

    public static void main(String[] args) {
        Arrays.fill(store,2);
        int[] temp = new int[N*2];
        Arrays.fill(temp,0);

        sol(0,"",temp);

    }

    private static void sol(int i, String s, int[] temp) {

        if(s.length()>N*2) return;
        if(s.length()==N*2){ System.out.println(s); return;}

        for(int j=i; j<N*2; j++){
            if(temp[j]<2){
                temp[j]++;
                sol(j+1,s+","+j, temp);
                temp[j]--;
            }
        }
    }
}
