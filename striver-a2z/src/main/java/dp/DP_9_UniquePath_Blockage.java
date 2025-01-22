package dp;

import java.util.Arrays;

public class DP_9_UniquePath_Blockage {

    static int[][] maze = {
            {0, 0, 0},
            {0, -1, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    public static void main(String[] args) {
        int res =solByRecc(0,0);
        System.out.println(res);
        System.out.println("///// Memo //////");
        int [][] memo = new int[maze.length][maze[0].length];

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
        System.out.println("///// DP //////");
        byDP();
    }

    private static int solByRecc(int y, int x) {
        if(y>= maze.length || x >= maze[0].length) return 0;
        if(maze[y][x]==-1) return 0;
        if(y==maze.length-1 && x==maze[0].length-1) return 1;

        return solByRecc(y+1, x) + solByRecc(y, x+1);

    }

    private static int memoization(int y, int x, int[][] memo) {
        if(y>= maze.length || x >= maze[0].length) return 0;
        if(maze[y][x]==-1) return 0;
        if(y==maze.length-1 && x==maze[0].length-1) return 1;

        if(memo[y][x]!=-1) return memo[y][x];



        return memo[y][x] = memoization(y+1, x, memo) + memoization(y, x+1, memo);

    }

    private static void byDP(){
        int[][] dp = new int[maze.length+1][maze[0].length+1];
        dp[0][1]=1;
        for(int y=1; y< dp.length; y++){
            for(int x=1; x< dp[0].length; x++){
                if(maze[y-1][x-1]==-1) {
                    dp[y][x]=0;
                } else {
                    dp[y][x]=dp[y-1][x]+dp[y][x-1];
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
