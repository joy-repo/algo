package com.divide_conquer;

//https://www.techiedelight.com/find-number-1s-sorted-binary-array/

public class NumberOf1sInBinaryArray {

  static   int[] nums = { 0, 0, 0, 0, 1, 1, 1 };

    public static void main(String[] args) {
        sol1();
        solLast();

    }

    private static int solLast() {

        int strt = 0;
        int end=nums.length-1;
        int res=0;

        while(end>=strt){
            int mid = (strt+end)/2;
            if(nums[mid]==0){
                res=mid;
                strt=strt+1;
                continue;
            }

            if(nums[mid]<0){
                strt=mid+1;
                continue;
            }
            if(nums[mid]>0){
                end=mid-1;
                continue;
            }
        }
        System.out.println(res);
        return res;

    }

    private static void sol1() {
        int strt = 0;
        int end = nums.length-1;
        int highest=0;

        while(strt<=end){
            int mid = (strt+end)/2;
            if(nums[mid]==0){
                strt=strt+1;
                highest=mid;
                continue;
            }
            if(nums[mid]>0){
                strt=mid+1;
            }
        }
        System.out.println(highest);
    }
}
