package com.practice.dp;

public class DiceThrow {

    static int faces=6;
    static int num=4;
    static int sum=15;

    public static void main(String[] args) {
        int res =solByrec(0,0);
        System.out.println(res);;
        bottomUpDP();
    }

    private static void bottomUpDP() {


    }

    private static int solByrec(int c, int s) {

        if(c==num) return s==sum? 1:0;

        if(s>=sum) return  0;
        int count=0;

        for(int i=1;i<=faces;i++){
            count = count+solByrec(c+1,s+i );
        }
        return count;
    }


}
