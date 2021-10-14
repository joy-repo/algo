//package com.practice.dp;
//
//public class BooleanParenthesization {
//
//    static boolean[] SYMBOL = {true, true, false, true};
//    static char[] OPERATOR = {'|', '&', '^'};
//
//    //T|T&F^T
//
//    // (((T | T)
//    // (T|((T
//
//    public static void main(String[] args) {
//        solByRec(0,"");
//    }
//
//    private static void solByRec(int c, int br,String exp) {
//
//        if(c==SYMBOL.length-1)  {
//            System.out.println(exp+SYMBOL[c]+getClosingBrackets(br));
//            return  ;
//        }
//
//
//    }
//
//    private static String getClosingBrackets(int br) {
//        String res ="";
//        while(br>0){
//            res=res+")";
//            br--;
//        }
//        return res;
//    }
//}
