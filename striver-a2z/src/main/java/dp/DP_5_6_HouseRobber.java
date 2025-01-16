package dp;

import java.util.Arrays;
import java.util.List;

public class DP_5_6_HouseRobber {

    static List<Integer> ARR = Arrays.asList(new Integer[]{8, 5, 1, 2, 6});
    static int RESCAlls =0;
    static int MEMCalls =0;
    static int MEM_No_Container_CALLS =0;

    public static void main(String[] args) {
//        int res = solve_RECC(0, false, ARR.size() - 1);
//        System.out.println(res);
//        res = solve_RECC(1, false, ARR.size());
//        System.out.print(res);

        int res = solve_RECC(0, false, ARR.size() );
        System.out.println(res);

        //////MEMOIZATION

        int[][] memo = new int[2][ARR.size()+1];
        Arrays.fill(memo[0], -1);
        Arrays.fill(memo[1], -1);

          res = memoization(0, false, 0, ARR.size(), memo);
          System.out.println(res);
         for(int[] tmp : memo){
             System.out.println();
             for(int i : tmp){
                 System.out.print(i +" ,");
             }
         }
        System.out.println();
        int[] dp = new int[ARR.size()+1];
         Arrays.fill(dp, -1);
        mySolveUtil(0, dp, ARR.size());
        Arrays.stream(dp).forEach(e-> System.out.print(e +","));
        System.out.println();

        System.out.println("RESCAlls: "+RESCAlls);
        System.out.println("MEMCalls: "+MEMCalls);
        System.out.println("MEM_No_Container_CALLS :"+ MEM_No_Container_CALLS);

    }

    private static int solve_RECC(int i, boolean isLastSelected,  int finalIndex) {
        RESCAlls++;
        if(i>finalIndex-1) return 0;
        if (i == finalIndex) return ARR.get(i);


            int sum1 = solve_RECC(i + 1, true,  finalIndex) + ARR.get(i);
            int sum2 = solve_RECC(i + 1, false,  finalIndex);
            return Math.max(sum1, sum2);

        }


    private static int memoization(int i, boolean isLastSelected, int summation, int finalIndex, int[][] memo){
        if (i == finalIndex)
            return summation;

        if(isLastSelected){
            if(memo[1][i]!=-1)
                return memo[1][i];
        } else {
            if(memo[0][i]!=-1)
                return memo[0][i];
        }
        MEMCalls++;
        if (!isLastSelected) {
             int sum1=memoization(i + 1, true, summation + ARR.get(i), finalIndex, memo);
             int sum2 = memoization(i + 1, false, summation, finalIndex, memo);
            memo[0][i]= Math.max(sum1, sum2);
            return memo[0][i];
        } else {
            memo[1][i] = memoization(i + 1, false, summation, finalIndex, memo);
            return memo[1][i+1];
        }
    }



    /////////////////// MEMOIZATION- without Containerization//////////////////
    ////TODO: Calling solveUtil(n-1, ARR, dp) without passing the summation Around


    static int solveUtil(int ind, int[] arr, int[] dp) {
        // If the index is negative, there are no elements left to consider.
        if (ind < 0) return 0;

        // If the index is 0, there is only one element to consider, so return its value.
        if (ind == 0) return arr[ind];

        // If we have already calculated the result for this index, return it.
        if (dp[ind] != -1) return dp[ind];

        // Calculate the maximum sum by either picking the current element or not picking it.
        int pick = arr[ind] + solveUtil(ind - 2, arr, dp);
        int nonPick = solveUtil(ind - 1, arr, dp);

        // Store the maximum of the two options in the dp array for future reference.
        return dp[ind] = Math.max(pick, nonPick);
    }

    ///TODO: How to call solveUtil(0, dp, n-1[final index])

    static int mySolveUtil(int ind, int[] dp, int finalIndex) {
        // If the index is negative, there are no elements left to consider.
        if (ind > finalIndex-1) return 0;

        // If the index is 0, there is only one element to consider, so return its value.
        if (ind == finalIndex-1) return ARR.get(ind);

        // If we have already calculated the result for this index, return it.
        if (dp[ind] != -1) return dp[ind];
        MEM_No_Container_CALLS++;
        // Calculate the maximum sum by either picking the current element or not picking it.
        int pick = ARR.get(ind) + mySolveUtil(ind + 2, dp, finalIndex);
        int nonPick = mySolveUtil(ind + 1, dp, finalIndex);

        // Store the maximum of the two options in the dp array for future reference.
         dp[ind] = Math.max(pick, nonPick);
         return dp[ind];
    }



}

