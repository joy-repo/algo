package com.practice.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {

    public static String temp_dictionary[] = {"mobile", "samsung", "sam", "sung",
            "man", "mango", "icecream", "and",
            "go", "i", "like", "ice", "cream"};
    public static Set<String> DICTIONARY = new HashSet<>();

    public static String WORD = "ilikesamsung";

    public static void main(String[] args) {
        DICTIONARY = new HashSet<String>(Arrays.asList(temp_dictionary));
        solByRec(0, "");
        solByDP();
    }

    private static void solByDP() {
        boolean[][] DP = new boolean[WORD.length()][WORD.length()];

        for(int i=0; i<WORD.length(); i++)
            DP[i][i]=DICTIONARY.contains(WORD.charAt(i)+"");



    }


    private static void solByRec(int s, String res) {

        if (s >= WORD.length() - 1) {
            System.out.println(res);
            return;
        }

        for (int i = s + 1; i <= WORD.length(); i++) {
            String subString = WORD.substring(s, i);
            if (DICTIONARY.contains(subString)) {
                solByRec(i, res + " " + subString);
            }
        }
    }
}
