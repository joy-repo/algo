package com.ola;

public class Prob1 {

    static String INPUT = "@!AaA$aAabbcdd1#ee$%";
    ///output --> a6b3c2g1

    //'A' <ch < 'Z' ||
    //i =

    public static void main(String[] args) {
        sol();

    }

    private static void sol() {

        char[] charrArr = INPUT.toCharArray();
        int i = 0;
        String res = "";
        while (i < charrArr.length) {

            //System.out.println(i);
            //Character.isAlphabetic(charrArr[i])

            if (Character.isAlphabetic(charrArr[i])) {
                res = res + charrArr[i];
                int rep = 1;

                while (Character.toUpperCase(charrArr[i]) == Character.toUpperCase(charrArr[i + 1])) {

                    rep++;
                    i++;
                    //  System.out.println(i);
                }
                res = res + rep;
                i++;

            } else {
                i++;
            }

        }
        System.out.println(res);


    }
}
