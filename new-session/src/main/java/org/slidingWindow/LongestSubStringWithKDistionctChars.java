package org.slidingWindow;

import java.util.HashMap;
import java.util.Map;



public class LongestSubStringWithKDistionctChars {

    static  String STR = "eceba";
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

        Map<Character, Integer> charFreqMap = new HashMap<>();

        while (end<STR.length()) {
            charFreqMap.merge(STR.charAt(end), 1, Integer::sum);

            while(charFreqMap.get(STR.charAt(end))>k){
                charFreqMap.merge(STR.charAt(start), -1, Integer::sum);
                if(charFreqMap.get(STR.charAt(start))==0){
                    charFreqMap.remove(STR.charAt(start));
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
