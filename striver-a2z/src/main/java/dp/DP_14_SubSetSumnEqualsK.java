package dp;

public class DP_14_SubSetSumnEqualsK {

    static int ARR[] = { 1, 2, 3, 4, 21, 22 };
    static int TARGET = 14;

    public static void main(String[] args) {
        System.out.println( solByRecc(0, 0));
    }

    private static boolean solByRecc(int idx, int sum) {

        if(sum==TARGET) return true;
        if(idx >=ARR.length) return false;
        return solByRecc(idx+1, sum+ARR[idx]) || solByRecc(idx+1,sum);
    }
}
