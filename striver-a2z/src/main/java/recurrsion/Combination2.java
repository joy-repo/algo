package recurrsion;

// https://www.youtube.com/watch?v=G1fRTGRxXU8&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=52

public class Combination2 {

    static int ARR[] = {1,1,1,2,2};
    static int TARGET = 4;

    public static void main(String[] args) {
        reccSol(0, "", 0);
    }

    private static void reccSol(int len, String str, int sum) {
        if(len==ARR.length){
            if(sum==TARGET){
                System.out.println(str);
            }
            return;
        }

        reccSol(len+1, str+","+len, sum+ARR[len]);
        reccSol(len+1, str, sum);
    }
}
