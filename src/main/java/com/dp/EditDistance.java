package com.dp;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EditDistance {

    static char[] chrArr1 = "abcdef".toCharArray();
    static char[] chrArr2 = "azced".toCharArray();
    static int[][] DP = new int[chrArr2.length + 1][chrArr1.length + 1];

    public static void main(String[] args) {
        System.out.println(solDP());
        // System.out.println(solRecc(0, 0));
    }

    public static int solRecc(int i1, int i2) {

        if (i1 == chrArr1.length)
            return chrArr2.length - i2;
        if (i2 == chrArr2.length)
            return chrArr1.length - i1;

        if (chrArr1[i1] == chrArr2[i2])
            return solRecc(i1 + 1, i2 + 1);
        else
            return 1 + Math.min(Math.min(solRecc(i1 + 1, i2), solRecc(i1, i2 + 1)), solRecc(i1 + 1, i2 + 1));

    }

    public static int solDP() {

        IntStream.range(0, chrArr1.length + 1).forEach(i -> DP[0][i] = i);
        IntStream.range(0, chrArr2.length + 1).forEach(j -> DP[j][0] = j);

        for (int y = 1; y <= chrArr2.length; y++) {
            for (int x = 1; x <= chrArr1.length; x++) {
                if (chrArr1[x - 1] == chrArr2[y - 1])
                    DP[y][x] = DP[y - 1][x - 1];
                else
                    DP[y][x] = 1 + Math.min(Math.min(DP[y - 1][x - 1], DP[y][x - 1]), DP[y - 1][x]);
            }
        }
        printActualEdits(DP, chrArr2, chrArr1);
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
        return DP[chrArr2.length][chrArr1.length];
    }

    public static void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                System.out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }

        }
    }
}


