package com.arrays;

import java.util.Arrays;

//Find minimum sum subarray of given size k

public class MinimumSumSubarray {
	
	public static int arr[] = {1, 4, 2, 10, 23, 3, 1, 0, 20};
	
	public static int K=4;

	public static void main(String[] args) {
		sol();

	}
	
	public static void sol() {
		
		int sum = Arrays.stream(arr).limit(K).sum();
		int minSum=sum;
		for( int i=K;i<arr.length;i++) {
			//System.out.println(i);
			minSum = Math.min(minSum, sum+arr[i]-arr[i-K]);
			sum=sum+arr[i]-arr[i-K];
		}
			
		
		System.out.println(minSum);
	}

}
