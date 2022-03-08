package com.test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TT {

    public static void main(String[] args) {

        Comparator<Integer> c = Comparator.comparing(i->0);

        Stream<String> s = Stream.of("2", "3", "4", "5");

        // using Collectors maxBy(Comparator comparator)
        // and finding the maximum element
        // in reverse order
        Optional<String> obj = s
                .collect(Collectors
                        .maxBy(Comparator
                                .reverseOrder()));

        List<Integer> ll = new ArrayList<>();

        Optional<Integer> t  =  ll.stream().collect(Collectors.maxBy(c));
//
//        ll.stream().mapToInt(i->i).sum();
//
//        List<Integer> lt = new ArrayList<>(new HashSet<>(ll));
//        Double f = new Double((3*1.0)/4);
//        System.out.println(f);
//
//      //  Arrays.copyOfRange()
//        List<String> llj = new LinkedList<>();
//
//        System.out.println(  Arrays.toString("ui uiu".split(" ")));

//        Set<String> ss = new HashSet<>();
//        Set<String> s1 = new HashSet<>();
//        String str = "lll";
//        char[] cArr = str.toCharArray();
//       // List<Character> hh = Arrays.stream(cArr)
//
//       // Set<Character> puzzleSet = new HashSet<Character>(hh);
//       int[]  nums1={1,3,5};
//        int sum1 = Arrays.stream(nums1).sum();
//        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b)->(b-a));
//        //int r  =minHeap.offer(8)-1;
//        System.out.println(minHeap.peek());
//        System.out.println(minHeap.poll());

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b.compareTo(a));
        priorityQueue.offer(2);
        priorityQueue.offer(3);
        priorityQueue.offer(1);
        priorityQueue.offer(2);

        priorityQueue.remove(2);
        for (int i = 0; i < 4; i++) {
            System.out.println(priorityQueue.poll());
        }


        System.out.println(priorityQueue);
        priorityQueue.remove(2);
        System.out.println(priorityQueue);

    }

    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {

        List<Set<Character>> wordSet = new ArrayList<>();

        for(String str : words){
            Set<Character> set = getSet(str);
            wordSet.add(set);
        }
        List<Integer> res = new ArrayList<>();


        for(String str: puzzles){
            int tempRes = 0;
            Set<Character> puzzleSet = getSet(str);
            for( Set<Character> temp : wordSet){
                boolean present = puzzleSet.containsAll(temp);
                present = present && temp.contains(str.charAt(0));
                // for(char c : temp){
                //     if(!puzzleSet.contains(c)) present = false;
                // }
                if(present) tempRes++;
            }
            res.add(tempRes);
        }
        return res;
    }

    private Set<Character> getSet(String str) {
        char[] cArr = str.toCharArray();
        Set<Character> set = new HashSet<>();
        for(char c : cArr){
            set.add(c);
        }
        return set;
    }


}
