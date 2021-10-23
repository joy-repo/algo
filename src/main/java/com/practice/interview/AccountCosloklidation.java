package com.practice.interview;

import java.util.*;
import java.util.stream.Collectors;

//https://www.youtube.com/watch?v=FaxSLmHBmhA&list=PLBtMh4xfa9FHU2hNv3cwiZY649x3Hd8rR&index=10
//Problem1
public class AccountCosloklidation {

    static String[][] accountList = new String[][]{
            {"john", "john@we.com", "john@12.com"},
            {"john", "john@we1.com", "john@13.com"},
            {"john", "john@we.com", "john@15.com"},
            {"mary", "mary@we1.com", "mary@13.com", "mary@mary.com"},
            {"mary", "mary@mary.com"}
    };

    static List<List<String>> inputList = Arrays.stream(accountList).map(Arrays::asList).collect(Collectors.toList());

    ///          --->  Map<Email,Node>
    ///          --> Node--> index , email --> 0,john@we.com
    ///
    ///   ----- method add --> O(1) * (n)
    ///   ------combine (N1,N2) --> O(1) * (n^2)
    ///              -- N1 - map.get(email1)
    //               -- N2 - map.get(email2)
    //               N1.index=N2.index --> if email1==email2
    //    ---- loop
    ///  -------- Map<Index,List<String>> res ---> O(n)
    ///-------------
    ///
    ///<Node, index>
    /// union -> email1, email2 --->>
        /// N1, N2--> same index
    ///



    public static void main(String[] args) {
        sol();
        sol_disjointSets();
   }

    private static void sol_disjointSets() {

       // DisjointSet ds = new DisjointSet();
    }




    private static void sol() {

        Map<String,Set<Integer>> map = new HashMap<>();
        for(int i=0; i<inputList.size();i++){
            for(int j=1; j<inputList.get(i).size();j++) {
                String s =inputList.get(i).get(j);
                map.putIfAbsent(s, new HashSet<>());
                Set<Integer> set = map.get(s);
                set.add(i);
                map.put(s,set);
            }
        }



        Set<Set<String>> res = new HashSet<>();

        for(Map.Entry<String,Set<Integer>> e : map.entrySet()){
            Set<String> temp = new HashSet<>();
            for(int i : e.getValue()){
                temp.addAll(new HashSet(inputList.get(i)));
                res.add(temp);
            }
        }
        System.out.println(map);
        System.out.println();
        res.stream().forEach(System.out::println);

    }
}
