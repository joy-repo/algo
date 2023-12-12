package com.interviewbit;

import java.util.ArrayList;


//https://www.interviewbit.com/problems/flip/

public class Flip {

    public  static String STR = "100101101";

    public static void main(String[] args) {
        System.out.println(flip(STR));
    }
//// 110011101111 11
///
//
//
// c_1=2 , c_0=2



    public static ArrayList<Integer> flip(String A) {

        ArrayList<Integer> res = new ArrayList<>();

        int start =-1;
        int end=-1;
        int start_temp=-1;
        int end_temp =-1;

        int c_0=0;
        int c_1 =0;

        int i=0;

        while (i<A.length()){

            if(A.charAt(i)=='1' && start_temp==-1) { i++; continue;}

            if(A.charAt(i)=='0'){
                start_temp=i;
                c_1=0;
            }

            while(i<A.length() && A.charAt(i)=='0'){
                end_temp=i;
                c_1++;
                i++;
            }


//            if(A.charAt(i)=='1'){
//                c_0=0;
//            }

            while(i<A.length() && A.charAt(i)=='1'){
                c_0++;
                i++;
            }

            if(c_0<c_1 && i<A.length()){
                end_temp=i-1;
             }
            if(start==-1 && start_temp!=-1){
                end=end_temp;
                start=start_temp;
            }

            if(end-start<end_temp-start_temp ){
                end=end_temp;
                start=start_temp;
            }
            if(c_1<c_0){
                start_temp=i+1;
            }
            c_0=0;
            c_1=0;


        }
        if(start==-1) return res;
        res.add(start+1);
        res.add(end+1);
        return res;

    }
}
