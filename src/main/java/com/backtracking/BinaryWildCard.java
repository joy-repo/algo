package com.backtracking;

public class BinaryWildCard {

    static char[] pattern = "1?11?00?1?".toCharArray();

    public static void main(String[] args) {
        //    solPrintAll("");
        int res = solNumSols("");
        System.out.println(res);
    }

    private static int solNumSols(String s) {

        // if (s.length() > pattern.length) return 0;

        if (s.length() == pattern.length) {
            System.out.println(s);
            return 1;
        }

        //int ret=0;
        if (pattern[s.length()] == '?') {
            return solNumSols(s + "0") + solNumSols(s + "1");
        } else {
            return solNumSols(s + pattern[s.length()]);
        }
        // return  ret;

    }

    private static void solPrintAll(String s) {

        if (s.length() > pattern.length) return;

        if (s.length() == pattern.length) {
            System.out.println(s);
            return;
        }

        if (pattern[s.length()] == '?') {
            solPrintAll(s + "0");
            solPrintAll(s + "1");
        } else {
            solPrintAll(s + pattern[s.length()]);
        }

    }
}
