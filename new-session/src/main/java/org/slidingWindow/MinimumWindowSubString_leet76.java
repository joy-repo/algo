package org.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubString_leet76 {

    static String STR = "ADOBECODEBANCG";
    static String SUB_STR = "ABC";

    /// O/P -> "BANC"

    public static void main(String[] args) {
        solveLeet76();
    }

    private static void solveLeet76() {

        Map<Character, Integer> subStrCharMap = new HashMap<>();

        for(char c : SUB_STR.toCharArray()){
           subStrCharMap.merge(c, 1, Integer::sum);
        }

        Map<Character, Integer> tempFreqMap = new HashMap<>();
        int res = Integer.MAX_VALUE;
        int res_Start = 0;
        int res_End = 0;
        int start =0;
        int end = 0;

        while(start <= end && end < STR.length()){

            if(subStrCharMap.containsKey(STR.charAt(end)))
                tempFreqMap.merge(STR.charAt(end),1, Integer::sum);


            while(tempFreqMap.equals(subStrCharMap) ){
                if(res>end-start+1){
                    res=end-start+1;
                    res_Start= start;
                    res_End=end;
                }

                if(start< end && subStrCharMap.containsKey(STR.charAt(start))){
                    tempFreqMap.merge(STR.charAt(start), -1, Integer::sum);
                    start++;
                    break;
                }
                start++;
            }

            end++;




        }

        System.out.println(res);
        System.out.println(res_End);
        System.out.println(res_Start);


    }
}
