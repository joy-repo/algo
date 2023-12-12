package com.backtracking;

public class PossibleNumbersFrom5Digits {

    static int N=4;

    static int[] arr={1,2,3,4,5,6};

    public static void main(String[] args) {
        //solRecRepetition("");
        boolean[] store = new boolean[arr.length];
       // solRecNORepetition("",store);

     //   solComb("", 0);
        testSol("",0);
    }

    private static void testSol(String s, int i ){

        if(s.length()==N) {System.out.println(s); return;}

        if(i>=arr.length) return;

        testSol(s+arr[i],i+1);
        testSol(s,i+1);
    }

    private static void solComb(String s, int j) {

        if(s.length()==N) {System.out.println(s); return;}

        for(int i=j;i< arr.length;i++) {
            solComb(s+arr[i],i+1);
        }
    }

    private static void solRecNORepetition(String s, boolean[] store) {

        if(s.length()==N) {System.out.println(s); return;}

        for(int i=0;i< arr.length;i++){
            if(!store[i]) {
                store[i]=true;
                solRecNORepetition(s + arr[i],store);
                store[i]=false;
            }
        }
    }

    private static void solRecRepetition(String s) {

        if(s.length()==N) {System.out.println(s); return;}

        for(int i=0;i< arr.length;i++){
            solRecRepetition(s+arr[i]);
        }

    }
}

