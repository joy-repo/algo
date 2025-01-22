package dp;

import java.util.Arrays;

// |.|.|.|
// |.|.|.|
// |.|.|.|
// |.|.|.|
public class DP_8_GridUniquePaths {

    static int M=4;
    static int N=3;

    public static void main(String[] args) {
        int res = solRec(1,1);
        System.out.println(res);
        System.out.println("///// Memo //////");
        int [][] memo = new int[M+1][N+1];
        for(int[] t : memo){
            Arrays.fill(t, -1);
        }
        res = memoization(1,1, memo);
        System.out.println(res);
        System.out.println("///// Geeks //////");
        res = numberOfPaths(1,1);
        System.out.println(res);
        System.out.println("///// DP //////");
        byDP();
    }

    private static int solRec(int m, int n) {
        if(m==M && n==N) return 1;
        if(m>M || n>N) return 0;

        int res1 = solRec(m+1, n);
        int res2 = solRec(m,n+1);
        return res1+res2;
    }

    private static int memoization(int m, int n, int[][] memo) {
        if(m==M && n==N)
            return 1;
        if(m>M || n>N)
            return 0;

        if(memo[m][n]!=-1)
            return memo[m][n];

        int res1 = memoization(m+1, n, memo);
        int res2 = memoization(m,n+1, memo);
        return memo[m][n] = res1+res2;
    }

    private static void byDP(){
        int[][] dp = new int[M+1][N+1];
//        for(int i=0; i<dp[0].length; i++){
//            dp[1][i] =1;
//        }
        dp[0][1]=1;
        for(int y=1; y<dp.length;y++){
            for(int x=1; x<dp[0].length; x++){
                int res1 = dp[y-1][x];
                int res2 = dp[y][x-1];
                dp[y][x]= res1+res2;
            }
        }

        for(int[] tmp : dp){
            System.out.println();
            for(int i : tmp){
                System.out.print(i +" ,");
            }
        }


    }

    static int numberOfPaths(int m, int n) {

        // If either given row number is first or
        // given column number is first
        if (m == M || n == N)
            return 1;

        return numberOfPaths(m + 1, n)
                + numberOfPaths(m, n + 1);
    }


}
