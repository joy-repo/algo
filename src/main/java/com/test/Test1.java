package com.test;

public class Test1 {

    static int[][] arr = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
    };

    public static void main(String[] args) {

        soll();
    }

    private static void soll() {

        for(int k =0; k<arr[0].length;k++){
            System.out.println();
            for (int i=0, j=0; i+k<arr[0].length;i++,j++){
                System.out.print(arr[j][i+k]+",");
            }
        }
    }
}
