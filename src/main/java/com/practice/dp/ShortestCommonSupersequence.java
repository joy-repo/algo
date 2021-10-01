package com.practice.dp;

import java.util.Arrays;

public class ShortestCommonSupersequence {

    static String STR1 = "ABCBDAB";
    static String STR2 = "BDCABA";


    public static void main(String[] args) {
        String res = solByRec(0, 0);
        System.out.println(res);
        solByDP();
    }


    private static String solByRec(int i, int j) {

        if (i > STR1.length() && j > STR2.length()) return "";

        if (i == STR1.length()) return STR2.substring(j, STR2.length());
        if (j == STR2.length()) return STR1.substring(i, STR1.length());

        String res = STR1 + STR2;

        if (STR1.charAt(i) == STR2.charAt(j)) {
            String temp = solByRec(i + 1, j + 1) + STR1.charAt(i);
            res = temp;

        } else {
            String temp1 = STR1.charAt(i) + solByRec(i + 1, j);
            String temp2 = STR2.charAt(j) + solByRec(i, j + 1);
            res = temp1.length() > temp2.length() ? temp2 : temp1;
        }
        return res;
    }

    private static void solByDP() {

        String[][] DP = new String[STR2.length()+1][STR1.length()+1];
        DP[0][0]="";
        for(int i=1; i<DP.length; i++)
            DP[i][0]=DP[i-1][0] +STR2.charAt(i-1);

        for(int j=1; j<DP[0].length; j++)
            DP[0][j]=DP[0][j-1] +STR1.charAt(j-1);

        for(int i=1; i<DP.length; i++) {
            for(int j=1;j<DP[0].length;j++){
                if(STR2.charAt(i-1)== STR1.charAt(j-1))
                    DP[i][j]=DP[i-1][j-1]+STR2.charAt(i-1);
                else if(DP[i-1][j] == DP[i][j-1])
                    DP[i][j]=DP[i-1][j]+STR2.charAt(i-1);
                else
                   if(DP[i-1][j].length()>DP[i][j-1].length())
                       DP[i][j]=DP[i][j-1]+STR1.charAt(j-1);
                   else
                       DP[i][j]=DP[i-1][j]+STR2.charAt(i-1);
            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);

    }

}
