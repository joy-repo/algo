package com.dp;

import java.util.Arrays;

public class CoinChange {

    static int coins[] = {1, 3, 5, 7};
    static int T = 23;

    public static void main(String[] args) {
        //int res = solByRec_PERMUTE(0, "");
        System.out.println("##### PERMUTE ########");
        System.out.println("COUNT :" +solByRec_PERMUTE(0, ""));
        System.out.println("##### COMBINE ########");
        System.out.println("COUNT :" +solByRec_COMBINE(0,0,""));
        solByDP();
       // countDP(coins,T);
    }

    private static int solByRec_PERMUTE(int sum,  String str) {

        if (sum == T) {
            System.out.println(str);
            return 1;
        }
        if (sum > T) return 0;
        int res = 0;
        for (int i =0; i<coins.length; i++) {
            res = res + solByRec_PERMUTE(sum + coins[i], str +  coins[i]+ "," );
        }
        return res;
    }

    private static int solByRec_COMBINE(int sum, int t, String str) {

        if (sum == T) {
            System.out.println(str);
            return 1;
        }
        if (sum > T) return 0;
        int res = 0;
        for (int i =t; i<coins.length; i++) {
            res = res + solByRec_COMBINE(sum + coins[i],i, str +  coins[i]+ "," );
        }
        return res;
    }

    private static void solByDP(){

        int DP[][] = new int[coins.length+1][T+1];
//        for(int i=0;i<coins.length+1;i++)
//            DP[i][0]=1;

        for(int j=1 ;j<=coins.length;j++){
            for(int i =0; i<=T;i++){
                if(coins[j-1]>i){
                    DP[j][i]=DP[j-1][i];
                } else if(coins[j-1]==i){
                    DP[j][i]=DP[j-1][i]+1;
                }else{
                    DP[j][i]=DP[j-1][i]+DP[j][i-coins[j-1]];
                }

            }
        }
        Arrays.stream(DP).map(Arrays::toString).forEach(System.out::println);

    }

//    public static void countDP(int[] S, int target)
//    {
//        int n = S.length;
//
//        int T[][] = new int[n + 1][target + 1];
//
//        for (int i = 0; i <= n; i++) {
//            T[i][0] = 1;
//        }
//
//        for (int i = 1; i <= n; i++)
//        {
//            for (int j = 1; j <= target; j++)
//            {
//                if (S[i - 1] > j) {
//                    T[i][j] = T[i - 1][j];
//                }
//                else {
//                    T[i][j] = T[i - 1][j] + T[i][j - S[i - 1]];
//                }
//            }
//        }
//
//        Arrays.stream(T).map(Arrays::toString).forEach(System.out::println);
//    }
}
