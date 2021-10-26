package com.leetcode;

import java.util.HashMap;
import java.util.Map;

public class Leet3 {

    static String STR = "bbb";//    "cdd";

    public static void main(String[] args) {

        sol_SlidingWindow();

    }

    private static void sol_SlidingWindow() {

        Map<Character,Integer> map = new HashMap<>();
        int res=0;
        int start=0;
        int end=0;


       while(end<STR.length()){

           if(!map.containsKey(STR.charAt(end))){
               map.put(STR.charAt(end), end);
               res=Math.max(res,end-start+1);
               end++;
           }

           else{
               int temp=start;
               start=map.get(STR.charAt(end))+1;
               for (int i=temp;i<start;i++ )
                   map.remove(STR.charAt(i));
            }
        }
       res=Math.max(res,end-start);
      // return res;
       System.out.println(res);
    }
}
