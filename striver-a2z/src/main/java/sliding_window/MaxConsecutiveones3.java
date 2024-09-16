package sliding_window;

public class MaxConsecutiveones3 {

    static int[] NUMS = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
    static int K = 3;

    public static void main(String[] args) {
        mcos3();

    }

    private static void mcos3() {
        int nzs = 0;
        int max = 0;
        int res = 0;

        int l=0;
        int r=0;

        while(r<NUMS.length){

            if(NUMS[r]==1){
                max++;
                res = Math.max(res, max);
                r++;
            } else {
                nzs++;
                if(nzs<=K){
                    max++;
                    res = Math.max(res, max);
                    r++;
                } else{
                    while (nzs>K){
                        if(NUMS[l]==0){
                            nzs--;
                            l++;
                            max--;
                        } else {
                            l++;
                            max--;
                        }
                    }
                    r++;
                    max++;
                }

            }
        }

        System.out.println("res: " + res);

    }
}
