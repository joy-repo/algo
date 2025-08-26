package org.slidingWindow;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class SlidingWindowMaximum {

    static int[] nums = {1,3,-1,-3,5,3,6,7};
    static int k = 3;
    public static void main(String[] args) {
        solSlidingWindowMax(nums, k);
    }

    private static void solSlidingWindowMax(int[] nums, int k) {

        Deque<Integer> dq = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<k; i++){
            while(!dq.isEmpty() && nums[dq.getLast()]<nums[i]){
                dq.removeLast();
            }
            dq.offerLast(i);
        }
        res.add(nums[dq.getFirst()]);
        for(int i=k; i<nums.length; i++){

            while(!dq.isEmpty() && dq.getFirst()<i-k+1){
                dq.removeFirst();
            }

            while(!dq.isEmpty() && nums[dq.getLast()]<nums[i]){
                dq.removeLast();
            }
            dq.offerLast(i);
            res.add(nums[dq.getFirst()]);

        }

        System.out.println(res);
    }
}