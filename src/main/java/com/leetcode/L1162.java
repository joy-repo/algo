package com.leetcode;

/*

Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
Output: true
Explanation:
word1 represents string "ab" + "c" -> "abc"
word2 represents string "a" + "bc" -> "abc"
The strings are the same, so return true.
 */

import java.util.Arrays;

public class L1162 {

    public static void main(String[] args) {

        String[] word1 = new String[]{"ab", "c"};
        String[]  word2 = new String[]{"a", "bc"};
        boolean res = arrayStringsAreEqual(word1,word2);
        System.out.println(res);
    }

    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {



       return Arrays.stream(word1).reduce("",(a1,a2)-> a1+a2 ).equals( Arrays.stream(word2).reduce("",(a1,a2)-> a1+a2 ));

    }

}
