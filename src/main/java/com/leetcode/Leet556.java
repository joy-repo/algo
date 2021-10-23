package com.leetcode;

import java.util.Arrays;

public class Leet556 {

    public static void main(String[] args) {
        nextGreaterElement(230241);  //2147483647
    }

    public static int nextGreaterElement(int n) {
        char[] charArr = (n+"").toCharArray();
        boolean NoRes =true;
        int i=0;

        for( i= charArr.length-1;i>0;i--){
            if(charArr[i]>charArr[i-1]){
                NoRes=false;
                break;
            }
        }
        if(NoRes) return -1;
        int l1=i;
        char minChar=charArr[l1-1];
        int minIndex=l1;


        for(int j=i;j<charArr.length;j++){
            if(charArr[l1-1]<charArr[j] && minChar>charArr[j]){
                minChar=charArr[j];
                minIndex=j;
            }
        }


        char temp =charArr[l1-1];
        charArr[l1-1]=charArr[minIndex];
//        charArr[i]= charArr[charArr.length-1];
        charArr[minIndex]=temp;
        System.out.println(charArr);
        Arrays.sort(charArr,l1,charArr.length);
        System.out.println(charArr);
        try{
            int res1 =  Integer.parseInt(new String(charArr));
            return res1;
        } catch(Exception e){
            return -1;
        }
    }


}
