package dp;

import java.util.Arrays;

public class DP_14_SubSetSumnEqualsK {

    static int ARR[] = { 1, 2, 3, 4, 7, 8 };
    static int TARGET = 14;

    public static void main(String[] args) {
        System.out.println( solByRecc(0, 0));

        System.out.println("///// Memo //////");
        int [][] memo = new int[ARR.length][TARGET];

        for(int[] t : memo)
            Arrays.fill(t, -1);

        int rr  = memoization(0,0, memo);
        System.out.println(rr);

        for(int[] tmp : memo){
            System.out.println();
            for(int i : tmp){
                System.out.print(i +" ,");
            }
        }
        System.out.println();
        System.out.println("///// DP //////");
        byDP();
    }

    private static boolean solByRecc(int idx, int sum) {

        if(sum==TARGET) return true;
        if(idx >=ARR.length) return false;
        return solByRecc(idx+1, sum+ARR[idx]) || solByRecc(idx+1,sum);
    }

    private static int memoization(int idx, int sum, int[][] memo) {
        if(sum>TARGET) return 0;
        if(sum==TARGET) return 1;
        if(idx >=ARR.length) return 0;
        if(memo[idx][sum]!=-1) return memo[idx][sum];
        if(memoization(idx+1, sum+ARR[idx], memo)==1 || memoization(idx+1, sum, memo)==1)
            return memo[idx][sum] = 1;

        return  memo[idx][sum] = 0;
    }

    private static void byDP(){
        boolean[][] dp = new boolean[TARGET+1][ARR.length+1];
        for(int i =0; i<dp[0].length; i++){
            dp[0][i]=true;
        }
        for(int sum=1; sum<dp.length; sum++){
            for(int i = 1 ; i<dp[0].length; i++){
                if(sum<ARR[i-1])
                    dp[sum][i] =  dp[sum][i-1];
                else
                    dp[sum][i] = dp[sum-ARR[i-1]][i-1] || dp[sum][i-1];
            }
        }
        for(boolean[] tmp : dp){
            System.out.println();
            for(boolean i : tmp){
                System.out.print(i +" ,");
            }
        }
    }
}
