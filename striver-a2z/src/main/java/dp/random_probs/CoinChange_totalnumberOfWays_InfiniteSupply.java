package dp.random_probs;

import java.util.Arrays;

public class CoinChange_totalnumberOfWays_InfiniteSupply {

    static int[] ARR = {1,5,6};
    static int TARGET = 28;
    // 5,5,5
    //
    public static void main(String[] args) {
        int res = solBYRecc_Permutation(TARGET);
        System.out.println("permutations :"+res);
         res = solBYRecc_Combination(TARGET, "", 0);
        System.out.println("combnation :"+res);
        System.out.println("//// DP- permutation ///");
        DP_Permutation();
        System.out.println("//// DP- combination ///");
        DP_Combination();
    }



    private static int solBYRecc_Permutation(int tar) {
        if(tar==0) return 1;
        if(tar<0) return 0;
        int res=0;
        for(int i =0; i<ARR.length;i++){
            res = res + solBYRecc_Permutation(tar-ARR[i]);
        }
        return res;
    }

    private static void DP_Permutation() {
        int[] DP = new int[TARGET+1];
        DP[0]=1;
        for(int tar=1; tar<=TARGET; tar++){
            int res=0;
            for(int i =0; i<ARR.length;i++){
                if(tar-ARR[i]>=0) {
                    int tmp = DP[tar - ARR[i]];
                    res = res + tmp;
                }
            }
            DP[tar]=res;
        }

        Arrays.stream(DP).forEach(e-> System.out.print(e +","));
        System.out.println();
    }

    private static int solBYRecc_Combination(int tar, String s, int kIndex) {
        if(tar==0) {
            // System.out.println(s);
            return 1;
        }
        if(tar<0) return 0;
        if(kIndex==3) return 0;

        int res =0;
        int tempTar =0;
        for(int i=0; tar>=tempTar; i++){
            tempTar=i*ARR[kIndex];
           // if(i>0) s=s+","+ARR[kIndex];
            res = res + solBYRecc_Combination(tar-tempTar, s, kIndex+1);
        }
        return  res;

    }

    private static void DP_Combination() {
        int[][] DP = new int[ARR.length+1][TARGET+1];

        for(int i = 0; i<DP.length; i++){
            DP[i][0]=1;
        }

         for(int y=1; y<DP.length; y++){
             for(int x=1; x<DP[0].length; x++){

                 int res =0;
                 int tempTar =0;
                 for(int i=0; x>=tempTar; i++){
                     tempTar=i*ARR[y-1];
                     if(x-tempTar>=0) {
                         int tmp = DP[y - 1][x - tempTar];
                         res = res + tmp;
                     }
                 }
                 DP[y][x]=res;
             }
         }
        for(int[] t : DP) {
            System.out.println();
            for (int r : t) {
                System.out.print(r + ",");
            }
        }

    }


//    private static int solBYRecc(int i, String ss, int kIndex) {
//        if(kIndex==ARR.length) return 0;
//        if(i<0) return 0;
//        if(i==0) {System.out.println(ss);return 1;}
//        int res=0;
//
//        int tmp_v =0;
//
//
//
//       do{
//           tmp_v=tmp_v+ARR[kIndex];
//           ss = ss + ","+ARR[kIndex];
//           int tmp = solBYRecc(i-tmp_v, ss,kIndex+1);
//
//           res=res+tmp;
//        }while(tmp_v < i);
//        return res;
//
//    }
}
