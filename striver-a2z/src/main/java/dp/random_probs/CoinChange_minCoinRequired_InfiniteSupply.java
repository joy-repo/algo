package dp.random_probs;

import java.util.Arrays;

public class CoinChange_minCoinRequired_InfiniteSupply {

    static int[] ARR = {1,5,6};
    static int TARGET = 15;

    public static void main(String[] args) {
        int res =solRECC(15);
        System.out.print(res);
        byDP();
    }

    private static int solRECC(int i) {
        if(i==0) return 1;
        if(i<0) return 0;
        int res = Integer.MAX_VALUE;
        for(int k=0; k<ARR.length; k++){
            int tmp = solRECC(i-ARR[k])+1;
            res = Math.min(tmp, res);
        }
        return res;
    }

    public static void byDP(){
        int[] DP = new int[TARGET+1];

        for(int i=1; i<=TARGET; i++){
            int res = Integer.MAX_VALUE;
            for(int k =0; k< ARR.length; k++){
                if(i-ARR[k] >=0) {
                    int tmp = DP[i - ARR[k]] + 1;
                    res = Math.min(tmp, res);
                }
                DP[i] = res;
            }
        }
        System.out.println();
        Arrays.stream(DP).forEach(e -> System.out.print(e +","));
    }

}
