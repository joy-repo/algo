package com.striver79.arrays_hashing;

public class Majority_N_by_3 {

    static int ARR[] = { 1, 4 ,4, 3, 1, 1 };

    public static void main(String[] args) {
        int ele1_ind =0;
        int ele2_ind =0;
        int cnt1=0;
        int cnt2 = 0;

        for(int i = 0; i<ARR.length; i++){
            if(ARR[ele2_ind]==ARR[i]){
                cnt2++;
            } else if (ARR[ele1_ind]==ARR[i]) {
                cnt1++;
            } else if(cnt1==0){
                ele1_ind = i;
                cnt1 = 1;
            } else if(cnt2==0){
                ele2_ind = i;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        System.out.println(ARR[ele1_ind]);
        System.out.println(ARR[ele2_ind]);


    }

}
