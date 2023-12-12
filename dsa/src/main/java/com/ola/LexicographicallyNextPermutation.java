package com.ola;

public class LexicographicallyNextPermutation {


    static String STR = "acbed";

    public static void main(String[] args) {
        String res = nextPermutation();
        System.out.println(res);
    }

    private static String nextPermutation() {

        ///acbde
        /// acbed
        /// 13254 --- 13425
        /// 12345

        char[] arrChar = STR.toCharArray();
        int store = arrChar.length-1;
        char val =arrChar[store];
        char[] res = new char[arrChar.length];

        for(int i=arrChar.length-1; i>0; i--){
            if(val>arrChar[i]){
                store=i;
                val=arrChar[i];
            }
            if(arrChar[i]>arrChar[i-1]){
                arrChar =createArr1(arrChar, store, val, i-1);
                break;
            }
        }
        return new String(arrChar);
    }

    private static char[] createArr1(char[] arrChar, int store, char val, int i) {

        char st = arrChar[i];

        for(int j =i ; j<arrChar.length; j++) {
           char temp = arrChar[j];
           arrChar[j]=st;
           st=temp;

        }
        arrChar[i]=val;
        return arrChar;

    }

    private static char[] createArr(char[] arrChar, int store, char val, int i) {

      //  arrChar[i-1]=arrChar[store];
        char st = arrChar[i-1];
        char[] arrRes = new char[arrChar.length];

        for(int j=0; j<=i; j++){
            arrRes[j]=arrChar[j];
        }



        for(int j =i ; j<arrChar.length; j++) {
            arrRes[j] = arrChar[j - 1];
        }
        arrRes[i]=arrChar[store];
        return arrRes;
    }
}


/// 1, 2, 3, 4 ,5 ,6
///