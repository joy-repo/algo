package org.slidingWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SlidingWindowMedian {

    
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        medianSlidingWindow(nums, k);
        // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
    
   private static void medianSlidingWindow(int[] nums, int k){

    TreeMap<Integer,Integer> left = new TreeMap<>();
    TreeMap<Integer,Integer> right = new TreeMap<>();

    List<Integer> res = new ArrayList<>();

    for(int i=0; i<k; i++){
        left.put(nums[i], left.getOrDefault(nums[i], 0)+1);
        
    }

    

    
   }

    // quick demo
}
