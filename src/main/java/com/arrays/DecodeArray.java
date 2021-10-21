package com.arrays;

import java.util.Arrays;

//Decode the array constructed from another array

public class DecodeArray {
	
	public static int[] arr = {3,4,5,6,5,6,7,7,8,9};
	
	public static void main(String[] args) {
		sol();
	}

	private static void sol() {
		
		
		
		// let the length of the result array be len.
		// arr.length = (len-1)+(len-2)+(len-3)+.....+(len-(len-2))+(len-(len-1))+0
		//arr.length = (len)^2-(1+2+3...+len)
		// arr.length = ((len)^2-len)/2
		//(len)^2-len = 2*arr.length
		//len(len-1)= 2*arr.length
		// ------> len = (int)Squrt(2*arr.length)+1
		
		int len = (int)Math.ceil(Math.sqrt(2*arr.length));
		
		int res[] = new int[len];
		
		
		// arr[0] = res[0]+res[1]-------(i)
		// arr[1] = res[0]+res[2]-------(ii)
		// adding (i)+(ii)
		// arr[0]+arr[1] = 2res[0]+(res[1]+res[2])
		// now, arr[len-1] =res[1]+res[2]
		// substituting,
		// res[0] = (arr[0]+arr[1]-arr[len-1])/2
		
		
		res[0] = (arr[0]+arr[1]-arr[len-1])/2;
		int l=0;
		for(int i=1; i<len;i++) {
			res[i]=arr[l]-res[i-1];
			l=l+len-i;
		}
		
		System.out.println(Arrays.toString(res));
	}

}
