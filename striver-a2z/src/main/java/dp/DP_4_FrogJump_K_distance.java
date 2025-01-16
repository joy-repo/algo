package dp;

import java.util.Arrays;

public class DP_4_FrogJump_K_distance {

    static int HEIGHT=30;
    static int K=4;

    public static void main(String[] args) {
        int res =sol_recc(0);
        System.out.println(res);

        /////MEMO//////////////

        int[] memo = new int[HEIGHT];
        Arrays.fill(memo,-1);
         res = memoization(0,memo);
         System.out.println(res);
         System.out.println();
         Arrays.stream(memo).forEach(e -> System.out.print(e +","));

         ///TABU/////////
        tabularization();
    }

    private static int sol_recc(int height) {
        if(height>HEIGHT) return 0;
        if(height==HEIGHT) return 1;
        int res =Integer.MAX_VALUE;
        for(int i=1; i<=K; i++){
            res = Math.min(sol_recc(height+i)+1,res);
        }
        return res;
    }

    private static int memoization(int height, int memo[]){
        if(height>HEIGHT) return 0;
        if(height==HEIGHT) return 1;
        if(memo[height]!=-1)
            return memo[height];
        int res =Integer.MAX_VALUE;
        for(int i=1; i<=K; i++){
            res = Math.min(memoization(height+i, memo)+1,res);
        }
        memo[height]=res;
        return memo[height];
    }

    private static void tabularization(){
        int[] dp = new int[HEIGHT+1];
        Arrays.fill(dp, -1);
        dp[0]= Integer.MAX_VALUE;
        for(int i=1; i<dp.length;i++){
            int res =Integer.MAX_VALUE;
            for(int j =1 ; j<=K; j++){
                if(i-j<0) break;
                res = Math.min(res, dp[i-j]);
            }
            dp[i]=Math.min(dp[i], res+1);
        }
        System.out.println();
        Arrays.stream(dp).forEach(e -> System.out.print(e + ","));
    }

}

