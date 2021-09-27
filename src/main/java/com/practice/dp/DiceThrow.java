package com.practice.dp;

import java.util.Arrays;

public class DiceThrow {

    static int faces = 6;
    static int num = 4;
    static int sum = 15;

    static int lookUp[][] = new int[num][sum];

    public static void main(String[] args) {
        //int res = solByrec(0, 0, "");
        int res = bottomUpDP(0, 0, "");
        System.out.println(res);
        for (int i = 0; i < lookUp.length; i++)
            System.out.println(Arrays.toString(lookUp[i]));

    }

    private static int bottomUpDP(int c, int s, String res) {
        if (c == num)
            if (s == sum) {
                System.out.println(res);
                return 1;
            } else return 0;

        if (s >= sum) return 0;
        int count = 0;


        if (lookUp[c][s] == 0) {

            for (int i = 1; i <= faces; i++) {
                count = count + bottomUpDP(c + 1, s + i, res + "," + i);
            }
            lookUp[c][s] = count;
        }
        return lookUp[c][s];

    }

    private static int solByrec(int c, int s, String res) {

        if (c == num)
            if (s == sum) {
                System.out.println(res);
                return 1;
            } else
                return 0;

        if (s >= sum) return 0;
        int count = 0;

        for (int i = 1; i <= faces; i++) {
            count = count + solByrec(c + 1, s + i, res + "," + i);
        }
        return count;
    }


}
