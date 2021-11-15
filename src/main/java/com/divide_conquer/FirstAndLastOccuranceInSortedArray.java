package com.divide_conquer;

//https://www.techiedelight.com/find-first-or-last-occurrence-of-a-given-number-sorted-array/

public class FirstAndLastOccuranceInSortedArray {

    static int[] ARR = {2, 5, 5, 5, 6, 6, 8, 9, 9, 9};
    static  int TARGET=5;

    public static void main(String[] args) {
        int first =solFirst();
        int last =solLast();
        System.out.println(first);
        System.out.println(last);
    }

    private static int solFirst() {

        int strt = 0;
        int end=ARR.length-1;
        int res=0;

        while(end>=strt){
            int mid = (strt+end)/2;
            if(ARR[mid]==TARGET){
                res=mid;
                end=end-1;
                continue;
            }

            if(ARR[mid]<TARGET){
                strt=mid+1;
                continue;
            }
            if(ARR[mid]>TARGET){
                end=mid-1;
                continue;
            }
        }

        return res;

    }

    private static int solLast() {

        int strt = 0;
        int end=ARR.length-1;
        int res=0;

        while(end>=strt){
            int mid = (strt+end)/2;
            if(ARR[mid]==TARGET){
                res=mid;
                strt=strt+1;
                continue;
            }

            if(ARR[mid]<TARGET){
                strt=mid+1;
                continue;
            }
            if(ARR[mid]>TARGET){
                end=mid-1;
                continue;
            }
        }

        return res;

    }


}
