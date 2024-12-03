package sliding_window;

public class BinarySubArraysWithSum {

   static int[] ARR = {1,0,0,1,1,0};
   static int GOAL = 2;

    public static void main(String[] args) {
        int l = 0;
        int r = 0;
        int res = 0;
        int sum = 0;

        while (r<ARR.length && l < ARR.length){
            sum = sum + ARR[r];

            if(sum > GOAL){
                l++;
            }

            if(sum == GOAL){
                res++;
                r++;
                continue;
            }
            while(sum > GOAL){
                sum = sum - ARR[l];
                l++;
            }
            if(sum==GOAL) res++;
            r++;
        }

        while (sum >= GOAL && l<r){
            sum =sum - ARR[l];
            if(sum == GOAL) {
                res++;
            }
            l++;
        }
        System.out.println(res);
    }
}
