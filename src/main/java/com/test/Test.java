package com.test;


/*
https://leetcode.com/problems/permutations/
https://leetcode.com/problems/permutations-ii/
https://leetcode.com/problems/next-permutation/
https://leetcode.com/problems/subsets/
https://leetcode.com/problems/subsets-ii/

 */

public class Test {

    public static void main(String[] args) {

       String yyu =  "ID_CARD_UPLOAD_FOLDER"+"//"+"899898";
       System.out.println(yyu);




        String[] str = "opop,uiui,uiui".split(",");
        System.out.println(str);
//        String ss = "MHYPDEMMXXX-FG12M-A-2022-02-09";
//
//        System.out.println(ss.substring(ss.length()-10,ss.length()));
//
//        List<String> strList = new ArrayList<>();
//
//        strList.add("1");
//        strList.add("A1");
//        strList.add("B1");
//        strList.add("2");
//        strList.add("A2");
//        strList.add("B2");
//
//        System.out.println(strList.stream().sorted().collect(Collectors.toList()));
//

        String s1 = "A10";
        String s2 = "A1";
        Integer s1R = Integer.parseInt(s1.trim().substring(1));
        Integer s2R = Integer.parseInt(s2.trim().substring(1));
        System.out.println(s1R);
        System.out.println(s2R);
        System.out.println(s1R.compareTo(s2R));


    }
}
