package dp.random_probs;


public class LongestPanindromicSubsequence {

    static char[] STR = "BBABCBCAB".toCharArray();

    public static void main(String[] args) {
        int res = lpsByRecc(0,STR.length-1, "");
        System.out.println(res);
    }

    private static int lpsByRecc(int start, int end, String res) {
        if(start>end) return 0;
        if(start==end) { System.out.println(res);return 1;}

        if(STR[start]==STR[end])
            return lpsByRecc(start+1,end-1, res+STR[start]+STR[end])+2;

        int tmp1 = lpsByRecc(start+1, end, res);
        int tmp2 = lpsByRecc(start, end-1, res);
        return Math.max(tmp1,tmp2);
    }

    private static void byDP(){
        int[][] DP = new int[STR.length+1][STR.length+1];


    }
}
