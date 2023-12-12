package com.practice.interview;

//https://www.youtube.com/watch?v=ekh3rlCS-4o&list=PLBtMh4xfa9FHU2hNv3cwiZY649x3Hd8rR&index=2
//Quetn1

import java.util.ArrayList;
import java.util.List;

public class PROB1 {

    static int N =9;
    static int K=3;
    static  List<List<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {

        solRECC(1,0, new ArrayList<>(),0);
        res.stream().forEach(System.out::println);
    }

    private static void solRECC( int i , int sum, List<Integer> list , int num ) {

        if(sum==N && num==K) { res.add(new ArrayList<>(list)); }

        for(int n=i; n<=N; n++){
            list.add(n);
            solRECC(n+1, sum+n,list, num+1);
            list.remove(list.lastIndexOf(n));
        }


    }
}
