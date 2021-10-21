package com.arrays;

import java.util.Arrays;

// Sort an array using one swap whose 2 elements are Swapped by Mistake
public class SortArrayInOneSwap {

	public static int[] arr = { 3, 8, 6, 7, 5, 9 };

	public static void main(String[] args) {
		sol();
	}

	private static void sol() {
		
		int i1 =0;
		for(int i=1; i<arr.length;i++) 
			if(arr[i-1]>arr[i]) {
				i1 = i-1;
				break;
			}
		int i2=0;
		for(int i=arr.length-2; i>=0;i--) 
			if(arr[i]>arr[i+1]) {
				i2 = i+1;
				break;
			}
		
		System.out.println(i1+"---"+arr[i1]);
		System.out.println(i2+"---"+arr[i2]);
		
		int temp = arr[i1];
		arr[i1]=arr[i2];
		arr[i2]=temp;
		System.out.println(Arrays.toString(arr));
		
	}

}
