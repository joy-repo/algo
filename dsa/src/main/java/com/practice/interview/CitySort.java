package com.practice.interview;

//https://www.youtube.com/watch?v=IA-D6J7kHYw&list=PLBtMh4xfa9FHU2hNv3cwiZY649x3Hd8rR&index=3
//Prob1

import java.util.*;
import java.util.stream.Collectors;

public class CitySort {

    static String[][] INPUT = {
            {"Se", "Sa", "De"},
            {"Ny", "Ph", "De"},
            {"va", "Mi", "Sa"}
    };

    //map--> city, , List-> cities we have to visit --> de= {se,sa,ny,ph}  --> O(n)
    // sort the map by SizeOfVal in descending -->O(nlogn)
    // first will take sizeOfVal with 0;  and add all to a list
    // sizeOfVal with 1 --> add
    //
    //

    static Set<String> cityLIST = Arrays.stream(INPUT).flatMap(s -> Arrays.stream(s)).collect(Collectors.toSet());
    /// Output --> Se, Va, Mi, Sa,Ny, Ph, De

    public static void main(String[] args) {
        bruteForce();
        topologicalSort();
    }

    private static void topologicalSort() {

        Map<String,Set<String>> map = new HashMap<>();
        for(String[] strArr : INPUT){
            List<String> ll = Arrays.asList(strArr);
            for(String str : ll){

            }

        }


    }

    private static void bruteForce() {
        List<String> list = new ArrayList<>();

        // System.out.println(cityCount);
        solRecc(list);
        System.out.println(list);
    }

    private static boolean solRecc(List<String> list) {
        if (list.size() == cityLIST.size()) {
            System.out.println(list); return  true;
        }
        for (String city : cityLIST) {
            if (!list.contains(city)) {
                if (ifPossible(list, city)) {
                    list.add(city);
                    if(solRecc(list)) return true;
                    list.remove(city);
                }
            }
        }
         return false;
    }

    private static boolean ifPossible(List<String> list, String city) {
        // boolean res= true;
        List<List<String>> hitList = Arrays.stream(INPUT).map(Arrays::asList)
                .filter(l -> l.contains(city)).collect(Collectors.toList());
        for (String c : list) {
            for (List<String> ll : hitList) {
                int index1 = ll.indexOf(c);
                if (index1 == -1) continue;
                int index2 = ll.indexOf(city);
                if (index1 > index2) return false;
            }
        }
        return true;
    }
}
