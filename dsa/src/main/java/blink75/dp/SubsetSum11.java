package blink75.dp;

public class SubsetSum11 {



    public static void main(String[] args) {

        int[] ARR = {2,4,8,1,0,7,6};
        int sum=0;
        int res =0;
        for(int i =0; i<ARR.length; i++){
            sum=0;
            for(int j=i; j< ARR.length; j++){
                sum=sum+ARR[j];
                if(sum==8){
                    res++;
                }
            }

        }
        System.out.println(res);
    }
}
