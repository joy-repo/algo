package com.arrays;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MaximumRectangularAreaHistogram {


    static int[] ARR = {2, 1, 2, 3, 1};

    public static void main(String[] args) {

        sol();


        MaximumRectangularAreaHistogram mh = new MaximumRectangularAreaHistogram();
        int input[] = {2, 1, 2, 3, 1};
        int maxArea = mh.maxHistogram(input);
        System.out.println(maxArea);
        assert maxArea == 12;

    }

    private static void sol() {

        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        int area = Integer.MIN_VALUE;
        int res = 0;
        int i = 1;

        for (i = 1; i < ARR.length; ) {
            if (stk.isEmpty() || ARR[i] >= ARR[stk.peek()]) {
                stk.push(ARR[i]);
                i++;
            } else {
                int top = stk.pop();
                if (stk.isEmpty()) {
                    area = ARR[top] * i;
                } else {
                    area = ARR[top] * (i - stk.peek() - 1);
                }
                res = Math.max(res, area);
            }
        }


        while (!stk.isEmpty()) {
            int top = stk.pop();
            if (stk.isEmpty()) {
                area = ARR[top] * i;
            } else {
                area = ARR[top] * (i - stk.peek() - 1);
            }
            res = Math.max(res, area);
        }

        System.out.println(res);

    }


    public int maxHistogram(int input[]) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int maxArea = 0;
        int area = 0;
        int i;
        for (i = 0; i < input.length; ) {
            if (stack.isEmpty() || input[stack.peekFirst()] <= input[i]) {
                stack.offerFirst(i++);
            } else {
                int top = stack.pollFirst();
                //if stack is empty means everything till i has to be
                //greater or equal to input[top] so get area by
                //input[top] * i;
                if (stack.isEmpty()) {
                    area = input[top] * i;
                }
                //if stack is not empty then everythin from i-1 to input.peek() + 1
                //has to be greater or equal to input[top]
                //so area = input[top]*(i - stack.peek() - 1);
                else {
                    area = input[top] * (i - stack.peekFirst() - 1);
                }
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }
        while (!stack.isEmpty()) {
            int top = stack.pollFirst();
            //if stack is empty means everything till i has to be
            //greater or equal to input[top] so get area by
            //input[top] * i;
            if (stack.isEmpty()) {
                area = input[top] * i;
            }
            //if stack is not empty then everything from i-1 to input.peek() + 1
            //has to be greater or equal to input[top]
            //so area = input[top]*(i - stack.peek() - 1);
            else {
                area = input[top] * (i - stack.peekFirst() - 1);
            }
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }

//    public static void main(String args[]){
//        MaximumRectangularAreaHistogram mh = new MaximumRectangularAreaHistogram();
//        int input[] = {2,2,2,6,1,5,4,2,2,2,2};
//        int maxArea = mh.maxHistogram(input);
//        //System.out.println(maxArea);
//        assert maxArea == 12;
//    }
}