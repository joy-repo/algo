package com.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak {

    public static String temp_dictionary[] = {"mobile", "samsung", "sam", "sung",
            "man", "mango", "icecream", "and", "a", "am", "ace",
            "go", "i", "like", "ice", "cream"};
    public static Set<String> DICTIONARY = new HashSet<>();

    public static String WORD = "iamasamsung";

    public static void main(String[] args) {
        DICTIONARY = new HashSet<String>(Arrays.asList(temp_dictionary));
        solByRec(0, "");
        solByDP();
        solByDP1();
    }

    private static void solByDP() {
        String[] DP = new String[WORD.length() + 1];


        DP[0] = "";
        // DP[1] = "i";

        for (int i = 1; i <= WORD.length(); i++) {
            // DP[i + 1] = null;
            for (int j = 0; j < i; j++) {
                String tmpStr = WORD.substring(j, i);
                if (DICTIONARY.contains(tmpStr) && DP[j] != null) {
                    DP[i] = DP[j] + " " + tmpStr;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(DP));

    }

    private static void solByDP1() {
        Boolean[] DP = new Boolean[WORD.length() + 1];


        DP[0] = true;
        // DP[1] = "i";

        for (int i = 1; i <= WORD.length(); i++) {
            // DP[i + 1] = null;
            for (int j = 0; j < i; j++) {
                String tmpStr = WORD.substring(j, i);
                DP[i] = DICTIONARY.contains(tmpStr) && DP[j];
                if (DP[i]) break;
            }
        }
        System.out.println(Arrays.toString(DP));
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
