package com.divide_conquer;

public class BinaySearch {

    static int[] ARR = {1,4,5,9,12,15,19,23};
    static int TARGET = 8;

    public static void main(String[] args) {
       int res = solByRecc(0,ARR.length);
       System.out.println(res);
       res = solByItr();
        System.out.println(res);
    }

    private static int solByItr() {
        int start = 0;
        int end =ARR.length;


        while(start<=end){
            int mid=(start+end)/2;
            if(TARGET==ARR[mid]) return mid;
            if(TARGET>ARR[mid])
                start=mid+1;
            if(TARGET<ARR[mid])
                end=mid-1;

        }
        return -1;
    }

    private static int solByRecc(int start, int end) {

        if(end<start)
            return -1;
        int mid=(start+end)/2;
        if(ARR[mid]==TARGET) return mid;
        if(ARR[mid]>TARGET)
           return solByRecc(start,mid-1);
        else
          return   solByRecc(mid+1,end);
    }

}
