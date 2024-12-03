package recurrsion;

public class CombinatinSum {

    static int[] ARR = {2,3,6,7};
    static int TARGET = 7;

    public static void main(String[] args) {
        solComSum("", 0);
    }

    private static void solComSum(String str, int sum) {

        if(sum==TARGET){
            System.out.println(str);
            return;
        }
        if(sum>TARGET) return;

        for(int i=0; i<ARR.length; i++){
            solComSum(str+","+ARR[i], sum+ARR[i]);
        }
    }
}
