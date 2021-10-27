package com.arrays;

public class CombinationPermutation {

    static int[] arr = {1, 2, 2};
    static int K = 3;

    public static void main(String[] args) {
        combNonRepetition(0, "");
        //   combRepetition( 0, "");
        int[] store = {0, 0, 0, 0, 0};
        int[] numEle = {1, 1, 1, 1, 1};
        //  permutaionNonRepetition("", store, numEle);


    }


    private static void permutaionNonRepetition(String s, int[] store, int[] numberOfElements) {

        if (s.length() == K) {
            System.out.println(s);
            return;
        }

        for (int n = 0; n < store.length; n++) {
            if (store[n] < numberOfElements[n]) {
                store[n]++;
                permutaionNonRepetition(s + arr[n], store, numberOfElements);
                store[n]--;
            }
        }

    }

    private static void combRepetition(int i, String s) {


        if (s.length() == K) {
            System.out.println(s);
            return;
        }

        for (int n = i; n < arr.length; n++)
            combRepetition(n, s + arr[n]);
    }


    public static void combNonRepetition(int i, String s) {

//        if (j > arr.length)
//            return;
        //System.out.println(s);
        if (s.length() == K) {
            System.out.println(s);
            return;
        }

        for (int n = i; n < arr.length; n++)
            combNonRepetition(n + 1, s + arr[n]);

    }


}
