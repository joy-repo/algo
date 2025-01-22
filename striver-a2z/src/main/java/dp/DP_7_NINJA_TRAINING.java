package dp;


import java.util.Arrays;

public class DP_7_NINJA_TRAINING {

    static int[][] POINTS = {
            {5,2,1}, //
            {6,1,3},
            {2,1,3},

    };

    /// 0    0    0  0
    /// 0    0    0  0
    /// 0    0    0  0
    /// 0    0    0  0

    public static void main(String[] args) {
        int res = byRecc(0, -1);
        System.out.println(res);
        int[][] memo = new int[POINTS.length][POINTS[0].length];
        for(int[] t : memo){
            Arrays.fill(t, -1);
        }
        res = memorization(0,-1,memo);
        System.out.println("/////MEMO/////");
        System.out.println(res);
        for(int[] t : memo){
            System.out.println();
            for(int i : t) {
                System.out.print(i + ",");
            }
        }

        byDP();
    }

    private static int byRecc(int dayIdx, int lastIdx) {
        if(dayIdx==POINTS.length) return 0;
        int res = 0;
        for(int i =0; i<POINTS[dayIdx].length; i++){
            if(i!=lastIdx || lastIdx==-1) {
                int tmp = byRecc(dayIdx + 1, i) + POINTS[dayIdx][i];
                res = Math.max(tmp, res);
            }
        }
        return res;
    }

    private static int memorization(int dayIdx, int lastIdx, int[][] memo) {
        if(dayIdx==POINTS.length) return 0;
        int res = 0;
        if( lastIdx!=-1 &&memo[dayIdx][lastIdx]!=-1 ) return memo[dayIdx][lastIdx];

        for(int i =0; i<POINTS[dayIdx].length; i++){
            if(i!=lastIdx || lastIdx==-1) {
                int tmp = memorization(dayIdx + 1, i, memo) + POINTS[dayIdx][i];
                res = Math.max(tmp, res);
            }
        }
        if(lastIdx!=-1)
            memo[dayIdx][lastIdx] = res;
        return res;
    }

    public static void bydp(){

        int[][] dp = new int[POINTS.length+1][POINTS[0].length+1];

        for(int y=1; y<dp.length; y++){
            for (int x=1; x<dp[0].length; x++){
                int res = 0;
                for(int i =0; i<POINTS[y-1].length; i++){
                    if(i!=y-1 || y==1) {
                        int tmp = dp[y - 1][x] + POINTS[y-1][i];
                        res = Math.max(tmp, res);
                    }
                }
                dp[y][x]=res;
            }
        }
                System.out.println();
        System.out.print("/////DP/////");

        for(int[] t : dp){
            System.out.println();
            for(int i : t) {
                System.out.print(i + ",");
            }
        }

    }

    private static void byDP() {
        int[][] DP = new int[POINTS.length+1][POINTS[0].length+1];

        for(int dayIdx =1; dayIdx<DP.length; dayIdx++){
            for(int lastIdx=1; lastIdx<DP[0].length; lastIdx++){
                int res = 0;
                for(int i =0; i<POINTS[dayIdx-1].length; i++){
                    if(i!=lastIdx || dayIdx==1) {
                        int tmp = DP[dayIdx - 1][lastIdx] + POINTS[dayIdx-1][i];
                        res = Math.max(tmp, res);
                    }
                }
                DP[dayIdx][lastIdx]=res;
            }
        }
        System.out.println();
        System.out.print("/////DP/////");

        for(int[] t : DP){
            System.out.println();
            for(int i : t) {
                System.out.print(i + ",");
            }
        }
    }


}
