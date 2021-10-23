package com.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Leet1775 {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 4, 5, 6};
        int[] nums2 = {6, 3, 3, 1, 4, 5, 3, 4, 1, 3, 4};
        usingHeap(nums2, nums1);
        gotFromDiscussions(nums2, nums1);
    }


    public static int usingHeap(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length * 6 || nums2.length > nums1.length * 6) return -1;

        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();

        if (sum2 < sum1) return usingHeap(nums2, nums1);

        int diff = sum2 - sum1;

        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (a - b));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));

        for (int i : nums1)
            minHeap.offer(i);

        for (int i : nums2)
            maxHeap.offer(i);

        int res = 0;

        while (diff > 0 && (!minHeap.isEmpty() || !maxHeap.isEmpty())) {

            if (minHeap.isEmpty()) {
                diff = diff - (maxHeap.poll() - 1);
                res++;
                continue;
            }

            if (maxHeap.isEmpty()) {
                diff = diff - (6 - minHeap.poll());
                res++;
                continue;
            }

            if ((6 - minHeap.peek()) <= (maxHeap.peek() - 1)) {
                diff = diff - (maxHeap.poll() - 1);
                res++;
            } else {
                diff = diff - (6 - minHeap.poll());
                res++;
            }


        }

        int t = diff > 0 ? -1 : res;
        System.out.println(t);
        return t;


    }


    public static int gotFromDiscussions(int[] nums1, int[] nums2) {

        if (nums1.length > nums2.length * 6 || nums2.length > nums1.length * 6) return -1;

        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();

        if (sum2 < sum1) return usingHeap(nums2, nums1);

        int diff = sum2 - sum1;

        int[] contents1 = new int[6];
        for (int i : nums1) {
            contents1[i]++;
        }

        int[] contents2 = new int[6];
        for (int i : nums2) {
            contents2[i]++;
        }

        int res = 0;
        int i = 6;
        int j = 1;
        while (diff > 0) {
            if (i == 0) {
                diff = diff - (i - 1) * contents2[j];
                res = res + contents2[j];
                j++;
                continue;
            }

            if (j == 7) {
                diff = diff - (6 - i) * contents1[i];
                res = res + contents1[i];
                i--;
                continue;
            }
            int t1 = (6 - i) * contents1[i];
            int t2 = (i - 1) * contents2[j];
            if (t1 > t2) {
                diff = diff - t1;
                res = res + contents1[i];
                i--;
            } else {
                diff = diff - t2;
                res = res + contents2[j];
                j++;
            }
        }
        return res;

    }
}
