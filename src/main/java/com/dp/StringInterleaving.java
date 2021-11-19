package com.dp;

public class StringInterleaving {

    static char[] str1 = "XXYM".toCharArray();
    static char[] str2 = "XXZT".toCharArray();
    static char[] str3 = "XXXZXYTM".toCharArray();

    public static void main(String[] args) {
        isInterleaved();
        isInterleavedRecc(0, 0);
    }

    private static boolean isInterleavedRecc(int i, int j) {

        if (i == str1.length && j == str2.length && i + j == str3.length) return true;

        if (i + j > str3.length) return false;

        if (i < str1.length && str3[i + j] == str1[i]) {
            boolean b1 = isInterleavedRecc(i + 1, j);
            if (b1) return b1;
        } else if (j < str2.length && str3[i + j] == str2[j]) {
            boolean b1 = isInterleavedRecc(i, j + 1);
            if (b1) return b1;
        }

        return false;
    }

    public static boolean isInterleaved() {
        boolean T[][] = new boolean[str1.length + 1][str2.length + 1];

        if (str1.length + str2.length != str3.length) {
            return false;
        }

        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T[i].length; j++) {
                int l = i + j - 1;
                if (i == 0 && j == 0) {
                    T[i][j] = true;
                } else if (i == 0) {
                    if (str3[l] == str2[j - 1]) {
                        T[i][j] = T[i][j - 1];
                    }
                } else if (j == 0) {
                    if (str1[i - 1] == str3[l]) {
                        T[i][j] = T[i - 1][j];
                    }
                } else {
                    T[i][j] = (str1[i - 1] == str3[l] ? T[i - 1][j] : false) || (str2[j - 1] == str3[l] ? T[i][j - 1] : false);
                }
            }
        }
        return T[str1.length][str2.length];
    }


}
