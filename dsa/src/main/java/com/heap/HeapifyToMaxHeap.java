package com.heap;

import java.util.Arrays;

public class HeapifyToMaxHeap {

    static int[] arr = { 1, 3, 5, 4, 6, 13, 10, 9, 8, 15, 17 };
    static int[] res = new int[arr.length];

    public static void main(String[] args) {
        toMinHeap();
        System.out.println(Arrays.toString(arr));
        sol();
        System.out.println(Arrays.toString(arr));
    }

    private static void toMinHeap() {
        for(int i = (arr.length-1)/2; i >=0; i--){
            heapifyToMinHeap(i);
        }
    }

    private static void sol() {

        for(int i = (arr.length-1)/2; i >=0; i--){
            heapifyToMaxHeap(i);
        }


    }

    private static void heapifyToMaxHeap(int n) {

       int l = arr.length-1;

        for(int i =n; i<= l;){
            if(l<2*(i+1)-1) break;
            if(l<2*(i+1)){
                int left = arr[2*(i+1)-1];
                if(left<arr[i]){
                    int t =arr[i];
                    arr[i]=left;
                    arr[2*(i+1)-1]=t;
                    break;
                }
            }
            int right = arr[2*(i+1)];
            int left = arr[2*(i+1)-1];
            if(Math.max(right,left)<arr[i]) break;
            if(left>right){
                int t =arr[i];
                arr[i]=left;
                arr[2*(i+1)-1]=t;
                i=2*(i+1)-1;
                ;
            } else {
                int t =arr[i];
                arr[i]=right;
                arr[2*(i+1)]=t;
                i=2*(i+1);
                ;
            }

        }

    }

    private static void heapifyToMinHeap(int n) {

        int l = arr.length-1;

        for(int i =n; i<= l;){
            if(l<2*(i+1)-1) break;
            if(l<2*(i+1)){
                int left = arr[2*(i+1)-1];
                if(left>arr[i]){
                    int t =arr[i];
                    arr[i]=left;
                    arr[2*(i+1)-1]=t;
                    break;
                }
            }
            int right = arr[2*(i+1)];
            int left = arr[2*(i+1)-1];
            if(Math.min(right,left)>arr[i]) break;
            if(left<right){
                int t =arr[i];
                arr[i]=left;
                arr[2*(i+1)-1]=t;
                i=2*(i+1)-1;
                ;
            } else {
                int t =arr[i];
                arr[i]=right;
                arr[2*(i+1)]=t;
                i=2*(i+1);
                ;
            }

        }

    }
}


