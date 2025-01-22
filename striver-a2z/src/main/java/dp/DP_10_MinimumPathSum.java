package dp;

import java.util.Arrays;

public class DP_10_MinimumPathSum {

    static int matrix[][] = {
            {5, 9, 6},
            {11, 5, 2}
    };

    public static void main(String[] args) {
        int res = solByRecc(0,0);
        System.out.println(res);
        System.out.println("///// Memo //////");
        int [][] memo = new int[matrix.length][matrix[0].length];

        for(int[] t : memo)
            Arrays.fill(t, -1);

        res = memoization(0,0, memo);
        System.out.println(res);
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

    private static int solByRecc(int y, int x) {

        if(y==matrix.length || x==matrix[0].length)
            return Integer.MAX_VALUE;
        if(y==matrix.length-1 && x==matrix[0].length-1)
            return matrix[y][x];

        return Math.min(solByRecc(y+1,x), solByRecc(y,x+1))+matrix[y][x];
    }

    private static int memoization(int y, int x, int[][] memo) {

        if(y==matrix.length || x==matrix[0].length)
            return Integer.MAX_VALUE;
        if(y==matrix.length-1 && x==matrix[0].length-1)
            return matrix[y][x];

        if(memo[y][x]!=-1) return memo[y][x];

        return memo[y][x] = Math.min(memoization(y+1,x, memo), memoization(y,x+1, memo))+matrix[y][x];
    }

    private static void byDP(){
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        //dp[0][1]=1;
        for(int y=1; y< dp.length; y++){
            for(int x=1; x< dp[0].length; x++){
                if(x==1) {
                    dp[y][x] = dp[y - 1][x] + matrix[y - 1][x - 1];
                } else if (y==1) {
                    dp[y][x] = dp[y][x-1] + matrix[y - 1][x - 1];
                } else {
                    dp[y][x] = Math.min(dp[y - 1][x], dp[y][x - 1]) + matrix[y - 1][x - 1];
                }
            }
        }

        for(int[] tmp : dp){
            System.out.println();
            for(int i : tmp){
                System.out.print(i +" ,");
            }
        }

    }
}
