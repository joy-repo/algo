package dp;

import java.util.Arrays;

public class Fibonacci {

    static int n = 5;

    public static void main(String[] args) {

       System.out.println( fibREC(n));

       int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        int res = fibREC_memo(n, memo);
        System.out.println("memo  -"+res);

        tabularization(n);
    }

    private static int fibREC(int i) {
       if(i==0) return 0;
        if(i==1) return 1;
        return fibREC(i-2) +fibREC(i-1);
    }

    private static int fibREC_memo(int i, int[] memo) {
        if(i==0) return 0;
        if(i==1) return 1;
        if(memo[i]==-1)
            memo[i]= fibREC(i-2) +fibREC(i-1);
        return memo[i];
    }

    private static void tabularization(int i){
        int DP[] = new int[n+1];
        Arrays.fill(DP, -1);
        DP[0] = 0;
        DP[1] = 1;
        for(int k=2; k<=n; k++){
            DP[k] = DP[k-1]+DP[k-2];
        }
        System.out.println();
        for (int g : DP){
            System.out.print(g+",");
        }
    }

}
