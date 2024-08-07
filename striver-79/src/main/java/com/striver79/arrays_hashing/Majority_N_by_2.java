package com.striver79.arrays_hashing;

public class Majority_N_by_2 {

    static int ARR[] = { 1, 3, 3, 1, 2, 1, 1 };

    public static void main(String[] args) {
        int res = findMajority();
        if(isMajority(res))
            System.out.println(res);
        else
            System.out.println("not available");
    }

    private static boolean isMajority(int res) {
        int cnt = 0;
        for(int val : ARR){
            if(val==res) cnt++;
        }
        return ARR.length/2 <= cnt;
    }

    private static int findMajority() {

        int majIndex = 0;
        int cnt = 0;

        for (int i = 0; i< ARR.length; i++){
            if(cnt==0){
                cnt=1;
                majIndex = i;
                continue;
            }
            if(ARR[i]==ARR[majIndex]){
                cnt++;

            } else {
                cnt--;
            }
        }
        System.out.println(ARR[majIndex]);
        return ARR[majIndex];
    }


}
