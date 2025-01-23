package dp.random_probs;

/*

Problem: You are given n fence posts and k colors.
You need to paint the fence such that no more than two adjacent posts have the same color.
Find the number of valid ways to paint the fence.

EXAMPLE:

Input: n = 3, k = 2
Output: 6
Explanation: The valid ways are: 121, 122, 212, 211, 212, 221.

 */

import java.util.Arrays;

public class PaintTheFence {

    static int NUMBER_OF_POSTS=3;
    static int NUMBER_OF_COLORS=4;

    static int NUMOfRECCalls=0;
    static int NUMOfMEMCalls=0;



    public static void main(String[] args) {
        int res = solByRECC(0,0, false);
        System.out.println(res);
        System.out.println("///// Memo //////");
        int [][][] memo = new int[NUMBER_OF_POSTS][NUMBER_OF_COLORS+1][2];

        for(int[][] t1 : memo)
            for(int [] t : t1)
                Arrays.fill(t, -1);

         res = memoization(0,0, false, memo);
        System.out.println(res);
        System.out.println("NUMOfRECCalls : " + NUMOfRECCalls);
        System.out.println("NUMOfMEMCalls : " + NUMOfMEMCalls);
    }

    private static int solByRECC(int n, int k, boolean isConsecutiveUsed) {
        NUMOfRECCalls++;
        if(n==NUMBER_OF_POSTS)
            return 1;
        if(n>NUMBER_OF_POSTS || k > NUMBER_OF_COLORS)
            return 0;
        int res = 0;
        for(int i=1; i<=NUMBER_OF_COLORS; i++){
            if(i!=k) {
                res = res + solByRECC(n + 1, i, isConsecutiveUsed);
            } else if(!isConsecutiveUsed){
                res = res + solByRECC(n+1, i, true);
            }
        }
        return res;

    }

    private static int memoization(int n, int k, boolean isConsecutiveUsed, int[][][] memo) {
        NUMOfMEMCalls++;
        if(n==NUMBER_OF_POSTS)
            return 1;
        if(n>NUMBER_OF_POSTS || k > NUMBER_OF_COLORS)
            return 0;
        if(!isConsecutiveUsed){
            if(memo[n][k][0]!=-1) return memo[n][k][0];
        } else{
            if(memo[n][k][1]!=-1) return memo[n][k][1];
        }
        int res = 0;
        for(int i=1; i<=NUMBER_OF_COLORS; i++){
            if(i!=k) {
                res = res + memoization(n + 1, i, isConsecutiveUsed, memo);
            } else if(!isConsecutiveUsed){
                res = res + memoization(n+1, i, true, memo);
            }
        }
        if(!isConsecutiveUsed){
            return memo[n][k][0] =res;
        } else{
             return memo[n][k][1]=res;
        }
    }
}