package com.arrays;

//Find duplicates in constant array with elements 0 to N-1 in O(1) space and O(n) time

public class DuplicateElementLimiTedRangeArray {


    static int arr[] = {1, 2, 3, 1, 3, 6, 6};

    public static void main(String[] args) {
        //printRepeating(arr, arr.length);
        printRepeating1(arr, arr.length);
    }

    static void printRepeating(int arr[], int size) {
        int i;
        System.out.println("The repeating elements are : ");

        for (i = 0; i < size; i++) {
            int j = Math.abs(arr[i]);
            if (arr[j] >= 0)
                arr[j] = -arr[j];
            else
                System.out.print(j + " ");
        }
    }

    static void printRepeating1(int arr[], int size) {
        int i;
        System.out.println("\nThe repeating elements are : ");

        for (i = 0; i < size; i++) {
            int j = arr[i]<0 ? arr[i]+100000: arr[i];
            if (arr[i] < -10)
                System.out.print(i + " ");
            else
                arr[j] = -100000 +arr[j];
        }
    }

}
