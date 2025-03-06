package sliding_window;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {

    static int[] arr = {4,0,-1,3,5,3,6,8};
    static int k = 3;

    public static void main(String[] args) {
        solveswm();
    }

    private static void solveswm() {

        int[] res = new int[arr.length-k+1];
        Deque<Integer> deque = new LinkedList<>() ;

        for(int i =0; i< arr.length; i++){
            while(!deque.isEmpty() && deque.peekFirst()<=i-k){
                deque.pollFirst();
            }
            while(!deque.isEmpty() && arr[deque.peekLast()]<arr[i]){
                deque.pollLast();
            }
            deque.offer(i);
            if(i>=k-1){
                res[i-(k-1)] = arr[deque.peekFirst()];
            }
        }

        for(int i : res){
            System.out.print(i +",");
        }


    }
}
