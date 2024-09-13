package stacks_queues.monotonic;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingMaxWindow {

    static int[] ARR = {1,3,-1,-3,5,3,7,1,6};
                       // 3, 3 , 5, 5
    static int K = 3;

    public static void main(String[] args) {
        slidingWinMax();
    }

    private static void slidingWinMax() {

        List<Integer> list = new ArrayList<>();

        Deque<Integer> dq = new LinkedList<>();
        int j=0;
        for(j=0; j<K; j++){
            while(!dq.isEmpty() && ARR[dq.peekLast()] < ARR[j]) {
                dq.removeLast();
            }
            dq.addLast(j);
        }
        System.out.println("j "+j);
        list.add(ARR[dq.peekFirst()]);

        for(int i=K; i<ARR.length; i++){

            while (!dq.isEmpty() && dq.peekFirst() <= i-K){
                dq.removeFirst();
            }

            while (!dq.isEmpty() && ARR[dq.peekLast()] < ARR[i]){
                dq.removeLast();
            }
            dq.addLast(i);

            list.add(ARR[dq.peekFirst()]);


        }
        System.out.println(list);

    }
}
