package com.leetcode;

public class Leet321 {

    public static void main(String[] args) {
        int[] nums1={6,0,4};
        int[] nums2 = {6,7};
        int k=5;
        maxNumber(nums1, nums2,k);
    }


    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int res=Integer.MIN_VALUE;

        for( int i=0; i<=k; i++){
            if(nums1.length< i) continue;
            int[] n1 = maxSize(nums1,i);
            if(nums2.length<k-i) continue;
            int[] n2 = maxSize(nums2,k-i);
            int[] nums = merge(n1,n2);
            int num = createIntFromArray(nums);
            res=Math.max(num,res);
        }
        System.out.println(res);
        int[] result = createIntArray(res);

        return result;

    }

    public static int[] createIntArray(int n){
        int[] res = new int[(n+"").length()];

        for(int i=res.length-1; i>=0; i--){
            res[i]=n%10;
            n=n/10;
        }
        return res;

    }

    public static int createIntFromArray(int[] n){
        int res = 0;
        for(int i : n)
            res=res*10+i;
        return res;

    }

    public static int[] merge(int[] n1, int[] n2){
        int res[]= new int[n1.length+n2.length];
        int c=0;
        int i1=0;
        int i2=0;
        while(c<res.length){

            if(i1<n1.length && i2 < n2.length){

                if(n1[i1]>=n2[i2]){
                    res[c]=n1[i1];
                    i1++;
                    c++;
                } else{
                    res[c]=n2[i2];
                    i2++;
                    c++;
                }
                continue;
            }

            if(i1>=n1.length){
                res[c]=n2[i2];
                i2++;
                c++;
                continue;
            }

            if(i2>=n2.length){
                res[c]=n1[i1];
                i1++;
                c++;

            }

        }
        return res;


    }

    public static int[] maxSize(int[] num, int k){

        int start=0;
        int end=num.length-k;
        int res[] = new int[k];
        int r=0;

        while(r<k){
            int max = Integer.MIN_VALUE;
            int maxIndex=-1;
            int i=start;
            for(i=start; i<=end; i++){
                if(max<num[i]){
                    max=num[i];
                    maxIndex=i;
                }
            }
            res[r]=max;
            r++;
            start=maxIndex+1;
            end=end+1;

        }
        return res;

    }
}
