package blink75;

import java.util.Arrays;

public class KSortedArray {

   static int arr[] = {10, 9, 8, 7, 4, 70, 60, 50};
   static int K = 4;

    public static void main(String[] args) {
        solInsertionSort();
    }

    private static void solInsertionSort() {

        for(int i=0; i< arr.length; i++){

            kSortUtil(i, Math.min(i+K, arr.length-1));

        }



        for(int i : arr){
            System.out.println(i);
        }

    }

    private static void kSortUtil(int start, int stop){
        int lowestIndex = -1;
        int minVal = Integer.MAX_VALUE;

        for(int i =start; i<=stop; i++){
            if(arr[i]<minVal){
                lowestIndex=i;
                minVal=arr[i];
            }
        }
        if(lowestIndex==start) return;
        int temp = arr[start];
        arr[start]=arr[lowestIndex];
        arr[lowestIndex]=temp;
    }

    private static void insertionSort(int start, int stop) {

        for(int i=start+1; i<=stop; i++){
            int key = arr[i];
            int j=i-1;
            while (j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j--;
            }
            arr[j+1]=key;
        }
    }
}
