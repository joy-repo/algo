package com.dp;

import java.util.Arrays;

public class MinimumEditDistance {

	static char[] str1 = "sunday".toCharArray();
	static char[] str2 = "saturday".toCharArray();
	
	
	public static void main(String[] args) {
		System.out.println(sol_rec(0,0));
		sol_DP();
	}
	
	
	private static void sol_DP() {
		int[][] DP = new int[str1.length+1][str2.length+1];
		
		for(int c=0; c<=str1.length;c++)
			DP[c][0]=c;
		
		for(int r=0; r<=str2.length;r++)
			DP[0][r]=r;
		
		for(int c=1; c<=str1.length;c++) {
			for(int r=1; r<=str2.length;r++) {
				if(str1[c-1]==str2[r-1])
					DP[c][r]=DP[c-1][r-1];
				else
					DP[c][r]=min(DP[c-1][r-1],DP[c-1][r],DP[c][r-1])+1;
			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
		
	}


	private static int sol_rec(int i1, int i2) {
		
		if(i1==str1.length)
			return str2.length-1-i1;
		
		if(i2==str2.length)
			return str1.length-1-i2;
		
		if(str1[i1]==str2[i2])
			return sol_rec(i1+1,i2+1);
		else
			return min(sol_rec(i1+1,i2)+1, sol_rec(i1,i2+1)+1, sol_rec(i1+1, i2+1)+1);
		
		
		
	}
	
	private static int min(int... arr) {

		return Arrays.stream(arr).min().getAsInt();
	}
}
