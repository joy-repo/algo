package recurrsion;

public class PrintAnySubSequenceWithSumK {

    static int[] arr = {17, 18, 6, 11, 2, 4, 5, 1};
    static int SUM = 6;

    public static void main(String[] args) {
        solAg(0, "", 0);
    }

    private static boolean solAg(int l, String s, int sum) {

        if(l>=arr.length){
            if(sum==SUM){
                System.out.println("111 S : " +s);
                return true;
            } else
                return false;
        }

        if(sum==SUM){
            System.out.println("222 S : " +s);
            return true;
        }

        return solAg(l+1, s, sum) || solAg(l+1, s+","+arr[l], sum+arr[l]);
    }

}
