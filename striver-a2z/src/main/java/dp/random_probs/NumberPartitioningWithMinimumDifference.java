package dp.random_probs;

// Number Partitioning with Minimum Difference
//	•	Problem: Given an array of positive integers, partition it into
//	two subsets such that the absolute difference between their sums is minimized.

//Example:
//  Input: [1, 6, 11, 5]
//  Output: 1
//Explanation: We can split into {1, 5, 6} and {11}, with sums 12 and 11.

import javax.print.DocFlavor.READER;
import java.util.Arrays;

public class NumberPartitioningWithMinimumDifference {

    static int[] ARR = {1,6,11,9};

    public static void main(String[] args) {
        int totalSum=Arrays.stream(ARR).sum();
        int res = solByRecc(0,0, totalSum);
        System.out.println(res);
        System.out.println();
        System.out.println("///// DP //////");
        byDP();
    }



    private static int solByRecc(int p1Sum, int idx, int totalSum){
        if(totalSum/2 ==p1Sum  ){
            return Math.abs(totalSum-p1Sum*2);
        }

        if(idx==ARR.length) return Integer.MAX_VALUE;

        if(idx==ARR.length-1){
            return Math.abs((totalSum-p1Sum)-p1Sum);
        }

        return Math.min(solByRecc(p1Sum+ARR[idx], idx+1, totalSum),
                solByRecc(p1Sum, idx+1, totalSum));
    }

    private static void byDP() {

        int total = Arrays.stream(ARR).sum();
        boolean[][] dp = new boolean[ARR.length+1][total+1];
        int res = Integer.MAX_VALUE;
        for(int i=0; i< dp.length;i++){
            dp[i][0]=true;
        }
        for(int n=1; n<dp.length; n++){
            for(int sum=1; sum<dp[0].length; sum++){
                if(ARR[n-1]>sum){
                    dp[n][sum] = dp[n-1][sum];
                } else {
                    dp[n][sum] = dp[n-1][sum] || dp[n-1][sum-ARR[n-1]];
                }
                if(dp[n][sum]){
                    res=Math.min(res, Math.abs((total-sum)-sum));
                }
            }
        }
        System.out.println(res);

        for(boolean[] tmp : dp){
            System.out.println();
            for(boolean i : tmp){
                System.out.print(i +" ,");
            }
        }
    }

}
