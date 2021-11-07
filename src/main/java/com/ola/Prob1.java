package com.ola;

public class Prob1 {

    static String INPUT = "@!AaAaAabbcdd1#ee$%";
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

            System.out.println(i);


            if (('A' <= charrArr[i] && 'Z' >= charrArr[i]) || ('a' <= charrArr[i] && 'z' >= charrArr[i])) {
                res = res + charrArr[i];
                int rep = 1;

                while (i < charrArr.length - 1 && (charrArr[i] + "").equalsIgnoreCase(charrArr[i + 1] + "")) {

                    rep++;
                    i++;
                    System.out.println(i);
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
