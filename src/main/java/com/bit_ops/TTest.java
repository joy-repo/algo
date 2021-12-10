package com.bit_ops;

public class TTest {


    public static void main(String[] args) {
        int ty = 75;
        System.out.println(Integer.toBinaryString(ty));

        int r = (1<<3);
        System.out.println(Integer.toBinaryString(r));

        int res = r ^ ty;
        System.out.println(Integer.toBinaryString(res));

       // System.out.println(r);
    }
}
