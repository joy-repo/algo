package com.ttt;

public class AllSubSequence {

    static String STR = "IAMGOOD";

    public static void main(String[] args) {
        solve(0, "");
    }

    private static void solve(int i, String str) {

        if(i>=STR.length()){
            System.out.println(str);
            return;
        }

        solve(i+1, str+STR.charAt(i));
        solve(i+1, str);



    }
}
