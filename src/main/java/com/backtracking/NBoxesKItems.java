package com.backtracking;

public class NBoxesKItems {

    static int N =4;
    static int K =2;

    public static void main(String[] args) {
       int res = sol(0, "");
       System.out.print(res);
    }

    private static int sol(int i, String str) {
        if(i==N) {System.out.println(str);return 1;}

        int res=0;
        for(int j =1; j<=K; j++){
            res=res+sol(i+1, str+j);
        }
        return  res;
    }
}
