package com.dp;

import java.util.Arrays;

public class RodCut {

   static int PRICE[] = {1, 5, 8, 9, 10, 17, 17, 20};
   static int LEN=PRICE.length;

    public static void main(String[] args) {
        System.out.println(solByRec(0));
        solByDP();
    }



    private static int solByRec(int l) {

        if(l>LEN) return 0;
        int res=0;
        for(int i =l+1; i<=LEN; i++){
            int temp = solByRec(i) + PRICE[i-l-1];
            res = Math.max(res,temp);
        }
        return res;
    }

    private static int solByRec2(int l) {

        if(l>LEN) return 0;
        int res=0;
        for(int i =1; i<=l; i++){
            int temp = solByRec2(LEN-i) + PRICE[i-l-1];
            res = Math.max(res,temp);
        }
        return res;
    }

    private static void solByDP() {

        int DP[][] =new int[PRICE.length+1][LEN+1];

        for(int i=0; i<= PRICE.length;i++)
            DP[0][i]=0;

        for(int j = 1 ; j<= PRICE.length; j++){
            for(int i=1; i<=LEN;i++){
                if(i<j)
                    DP[j][i]=DP[j-1][i];
                else
                    DP[j][i]=Math.max(DP[j][i-j]+PRICE[j-1],DP[j-1][i]);
            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);
    }


}
