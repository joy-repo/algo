package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet1178 {

    public static void main(String[] args) {
        String[] words = {
                "aaaa", "asas", "able", "ability", "actt", "actor", "access"};
        String[] puzzles = {
                "aboveyz", "abrodyz", "abslute", "absoryz", "actresz", "gaswxyz"};
        List<Integer> ll = findNumOfValidWords(words,puzzles);
        System.out.println(ll);
    }

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        List<Set<Character>> wordSet = new ArrayList<>();

        for (String str : words) {
            Set<Character> set = getSet(str);
            wordSet.add(set);
        }
        List<Integer> res = new ArrayList<>();


        for (String str : puzzles) {
            int tempRes = 0;
            Set<Character> puzzleSet = getSet(str);
            for (Set<Character> temp : wordSet) {
                boolean present = puzzleSet.containsAll(temp);
                present = present && temp.contains(str.charAt(0));
                // for(char c : temp){
                //     if(!puzzleSet.contains(c)) present = false;
                // }
                if (present) tempRes++;
            }
            res.add(tempRes);
        }
        return res;
    }

    private static Set<Character> getSet(String str) {
        char[] cArr = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for (char c : cArr) {
            set.add(c);
        }
        return set;
    }
}
