package com.ola;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Prob2 {

    static String input = "a^b$c";

    ///
    /// map <INTeger, Chr> --> 1 , ','

    // string res ===>
    public static void main(String[] args) {
        sol();
        sol1();
    }

    private static void sol1() {
        int i = 0;
        int j = input.length() - 1;
        char[] cArr = input.toCharArray();

        while (i < j) {

            if (Character.isAlphabetic(cArr[i]) && Character.isAlphabetic(cArr[j])) {
                char t = cArr[i];
                cArr[i] = cArr[j];
                cArr[j] = t;
                i++;
                j--;
            } else if (Character.isAlphabetic(cArr[i]))
                j++;
            else if (Character.isAlphabetic(cArr[j]))
                i++;
            else {
                i++;
                j--;
            }


        }
        System.out.println(new String(cArr));
    }

    private static void sol() {
        String res = "";
        Map<Integer, Character> map = new HashMap<>();
        int i = -1;
        for (char c : input.toCharArray()) {
            i++;
            if (Character.isAlphabetic(c)) {
                res = c + res;
            } else {
                map.put(i, c);
            }

        }
        char[] carr = res.toCharArray();
        List<Character> ll = new LinkedList<Character>();
        for (char c : carr) {
            ll.add(c);
        }
        for (Map.Entry<Integer, Character> s : map.entrySet()) {
            ll.add(s.getKey(), s.getValue());
        }
        System.out.println(ll);
        String res1 = "";
        for (Character c : ll)
            res1 = res1 + c;
        System.out.println(res1);
    }


}
