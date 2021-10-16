package com.practice;

public class IndexOfZeroToBeReplacedForMaximum1s {

    static int[] ARR = { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 };

    public static void main(String[] args) {
        findIndex();
    }

    private static void findIndex() {

        int prevSum=0;
        int currSum=0;
        int res=0;
        int resPos=0;
        int prev0Pos=0;

        for(int i=0;i<ARR.length;i++){
            if(ARR[i]==1){
                currSum++;
                continue;
            }

            if(ARR[i]==0){
                if(res<prevSum+currSum+1){
                    resPos=prev0Pos;
                    res=prevSum+currSum+1;
                }
                prevSum=currSum;
                currSum=0;
                prev0Pos=i;
            }
        }

        if(res<prevSum+currSum+1){
            resPos=prev0Pos;
            res=prevSum+currSum+1;
        }
        System.out.println(res);
        System.out.println(resPos);

    }
}
