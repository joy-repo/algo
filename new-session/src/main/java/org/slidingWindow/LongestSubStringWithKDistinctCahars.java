package org.slidingWindow;

import java.util.Map;

import java.util.HashMap;


public class LongestSubStringWithKDistinctCahars {

    static String STR = "eceba";
    static int k = 2;   // O/P -> "ece"
    public static void main(String[] args) {
        solveLeet340();
    }

    private static void solveLeet340() {
        int start=0;
        int end=0;
        int res=0; 
        int res_Strt=0;
        int res_End=0;
        Map<Character,Integer> charSet = new HashMap<Character, Integer>();
       while (end<STR.length()) {
        charSet.merge(STR.charAt(end), 1, Integer::sum);

        while(charSet.size()>k){
            charSet.merge(STR.charAt(start), -1, Integer::sum);
            if(charSet.get(STR.charAt(start))==0){
                charSet.remove(STR.charAt(start));
            }
            start++;
        }
        if(res<end-start+1){
            res= end-start+1;
            res_Strt=start;
            res_End=end;
        }
        
        end++;
        
       }

        System.out.println(STR.substring(res_Strt, res_End+1));
           
    }
}
