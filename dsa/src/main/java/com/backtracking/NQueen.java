package com.backtracking;

import java.util.Arrays;

public class NQueen {

    static char[][] mat = new char[4][4];


    public static void main(String[] args) {
        // `mat[][]` keeps track of the position of queens in
        // the current configuration


        // initialize `mat[][]` by `-`
        for (int i = 0; i < mat[0].length; i++) {
            Arrays.fill(mat[i], '–');
        }

        sol(0, 0);
        for(int c=0; c<mat[0].length; c++){
            System.out.println();
            for (int r =0 ; r <mat.length; r++){
                System.out.print(mat[c][r]);
            }
        }
    }

    private static boolean sol(int x, int y) {


        if (hasCompleted()) {
            printArr();
            return true;
        }

        if (!isSafe(x,y)) return false;

        mat[y][x] = 'Q';
        if(sol(x + 1, y)) return true;
        if(sol(x, y + 1)) return true;

        return false;

    }

    private static void printArr() {
        for (int i = 0; i < 8; i++) {
            System.out.println(Arrays.toString(mat[i]).replaceAll(",", ""));
        }
        System.out.println();
    }

    private static boolean hasCompleted() {

        int num=0;
        for(char[] cArr : mat) {
            num = 0;
            for (char c : cArr) {
                if(c=='Q') num++;
            }
            if(num!=1) return false;
        }
        return true;
    }

    static boolean isSafe(int x, int y) {
        // return false if two queens share the same column

        if(x>7 || y>7) return  false;




        for (int i = 0; i < x; i++) {
            if (mat[i][y] == 'Q') {
                return false;
            }
        }

        for (int i = 0; i < y; i++) {
            if (mat[x][i] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `` diagonal
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = x, j = y; i < mat.length && j < mat.length; i++, j++) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        // return false if two queens share the same `/` diagonal
        for (int i = x, j = y; i >= 0 && j < mat.length; i--, j++) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        for (int i = x, j = y; i < 8 && j >= 0; i++, j--) {
            if (mat[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
