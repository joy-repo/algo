package com.practice.dp;


import java.util.Arrays;
import java.util.HashMap;

import java.util.Map;

public class BoxStacking {

    static int[] ln = new int[]{4, 1, 4, 10};
    static int[] br = new int[]{6, 2, 5, 12};
    static int[] ht = new int[]{7, 3, 6, 32};

    static Map<Integer, Box[]> maps = new HashMap<>();

    static void createBoxes() {
        for (int i = 0; i < ln.length; i++) {
            Box[] tempBoxes = new Box[3];
            tempBoxes[0] = (new Box(ln[i], br[i], ht[i]));
            tempBoxes[1] = (new Box(ht[i], ln[i], br[i]));
            tempBoxes[2] = (new Box(ht[i], br[i], ln[i]));
            maps.put(i, tempBoxes);
        }
        printBoxes();
    }

    private static void printBoxes() {

        maps.values().stream().flatMap(Arrays::stream).forEach(System.out::println);
    }

    public static void main(String[] args) {
        solByRecursion();
        solByDP();
    }

    private static void solByDP() {


    }


    static void solByRecursion() {
        createBoxes();
        boolean[] taken = new boolean[ln.length];
        Arrays.fill(taken, false);
        int res = solRec(0, taken);
        System.out.println(res);
    }



    static int solRec(int area, boolean[] taken) {

//        if ( ) {
//            return 0;
//        }

        int max = 0;
        for (int i = 0; i < ln.length; i++) {
            if (!taken[i]) {
                Box[] bxs = maps.get(i);
                taken[i] = true;
                for (int j = 0; j < 3; j++) {
                    int temp=0;
                    if (area < bxs[j].len * bxs[j].brd) {
                        temp = Math.max(solRec(bxs[j].len * bxs[j].brd, taken), temp);
                        max=Math.max(temp+bxs[j].hgt,max);
                    }
                }

                taken[i] = false;
            }
        }

        return max;


    }


    static class Box implements Comparable<Box>{

        public int len;
        public int brd;
        public int hgt;

        public Box(int len, int brd, int hgt) {
            this.len = len;
            this.brd = brd;
            this.hgt = hgt;
        }

        @Override
        public String toString() {
            return "Box{" +
                    "len=" + len +
                    ", brd=" + brd +
                    ", hgt=" + hgt +
                    '}';
        }

        @Override
        public int compareTo(Box b) {
            return (new Integer(len*brd).compareTo(new Integer(b.len*b.brd)));
        }
    }
}
