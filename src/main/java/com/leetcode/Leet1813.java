package com.leetcode;

import java.util.LinkedList;
import java.util.List;

public class Leet1813 {

    public static void main(String[] args) {
        boolean ggh =areSentencesSimilar("MTNhOb epj zqI ght dR", "MTNhOb ght dR");
        System.out.println(ggh);
    }


    public static boolean areSentencesSimilar(String sentence1, String sentence2) {

        if(sentence2.length()>sentence1.length()) return areSentencesSimilar(sentence2,sentence1);

        if(sentence1.equals(sentence2)) return true;


        // if(sentence1.toLowerCase().contains(sentence2.toLowerCase()))
        //    return true;

        List<String> list1 = new LinkedList<>();

        for(String str : sentence1.split(" ")){
            list1.add(str);
        }

        List<String> list2 = new LinkedList<>();

        for(String str : sentence2.split(" ")){
            list2.add(str);
        }

        boolean start = false;
        int i=0;
        int j=0;

        for( i=0 ,j=0; i<list1.size() && j<list2.size(); i++,j++){

            if(!list1.get(i).equals(list2.get(j))) break;
            if(j==list2.size()-1) return true;

        }

        int midi =i;
        int midj =j-1;


        for( i=list1.size()-1 ,j=list2.size()-1; i>=0 && j>=0; i--,j--){

            if(!list1.get(i).equals(list2.get(j))) break;
            if(j==0) return true;

        }

        for( i=list1.size()-1 ,j=list2.size()-1; i>=0 && j>=0; i--,j--){

            if(!list1.get(i).equals(list2.get(j))) break;
            System.out.println("j--"+j);
            System.out.println("list2.size()--"+list2.size());
            System.out.println("midj--"+midj);
          //  if(list2.size()==2 && midj==0) return  true;
            if(j==list2.size()-midj) return true;

        }


        return false;

    }
}
