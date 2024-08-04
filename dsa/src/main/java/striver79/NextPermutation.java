package striver79;


import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

///https://takeuforward.org/data-structure/next_permutation-find-next-lexicographically-greater-permutation/
public class NextPermutation {

    public static int[] arr = {1, 4, 6, 5, 3};

    // 1 4 6 5 3
    public static void main(String[] args) {
          sol1();
    }

    private static void sol1() {

        int max = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int i = arr.length-2;

        while (i >=0){
            if(arr[i] < arr[i+1]){
                break;
            }
            i--;
        }

        int brkpnt = arr[i];
        int k = arr.length-1;
        while(arr[k]< brkpnt ){
            k--;
        }

        arr[i] = arr[k];
        arr[k] = brkpnt;



        int end = arr.length-1;
        int start = i+1;

        while (end>start){
            int swap = arr[start];
            arr[start] = arr[end];
            arr[end] = swap;
             end--;
             start++;
        }



       Arrays.stream(arr).forEach(System.out::println);

    }
}
