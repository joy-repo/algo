package com.ola;


import java.util.Arrays;

//https://www.geeksforgeeks.org/find-number-times-string-occurs-given-string/
public class NumOccurASSubSequence {

    static char[] STR1 = "geeksforgeeks".toCharArray();
    static  char[] STR2 = "gks".toCharArray();

    public static void main(String[] args) {
        int res = getByDP();
        System.out.println(res);
    }

    private static int getByDP() {

        int[][] DP = new int[STR2.length+1][STR1.length+1];

        for(int y=1; y<=STR2.length;y++){
            for(int x = 1 ; x<=STR1.length;x++){

                if(y==1 && STR1[x-1]==STR2[y-1]){
                    DP[y][x]=DP[y][x-1]+1;
                    continue;
                }

                if(STR1[x-1]==STR2[y-1])
                    DP[y][x]=DP[y-1][x]+DP[y][x-1];
                else {
                 //   System.out.println("y="+y+"---x="+x+"---STR1[x-1]="+STR1[x-1]+"-----STR2[y-1]");
                    DP[y][x] = DP[y][x-1];
                }
            }
        }

        Arrays.stream(DP).forEach(a-> System.out.println(Arrays.toString(a)));
        return DP[STR2.length][STR1.length];
    }
}
