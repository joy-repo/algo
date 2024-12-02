package recurrsion;

public class CountAllSubSequencesWithSumK {

    static int[] arr = {17, 18, 6, 11, 2, 4, 5, 1};
    static int SUM = 6;

    public static void main(String[] args) {
        solRR(0, "", 0);
    }

    private static void solRR(int l, String s, int sum) {
        if(l>= arr.length){
            if(sum==SUM) {
                System.out.println("!! sum:" + sum + " l: " + l +"--- s :"+ s);

            }
            return;
        }



        solRR(l+1, s+","+arr[l], sum+arr[l]);
        solRR(l+1, s, sum);

    }
}
