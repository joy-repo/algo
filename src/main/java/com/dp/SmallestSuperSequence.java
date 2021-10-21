package com.dp;

import java.util.Arrays;

public class SmallestSuperSequence {
	
	static char[] str1 = "AGGTAB".toCharArray();
	static char[] str2 = "GXTXAYB".toCharArray();
	
	public static void main(String[] args) {
		System.out.println(sol_rec(0,0));
		sol_DP();
	}

	private static void sol_DP() {
		int m = str1.length, n = str2.length;

		// lookup table stores solution to already computed sub-problems
		// i.e. T[i][j] stores the length of SCS of substring
		// X[0..i-1] and Y[0..j-1]
		int[][] DP = new int[m + 1][n + 1];

		// initialize first column of the lookup table
		for (int i = 0; i <= m; i++) {
			DP[i][0] = i;
		}

		// initialize first row of the lookup table
		for (int j = 0; j <= n; j++) {
			DP[0][j] = j;
		}

		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= m; i++)
		{
			for (int j = 1; j <= n; j++)
			{
				// if current character of X and Y matches
				if (str1[i - 1] == str2[j - 1]) {
					DP[i][j] = DP[i - 1][j - 1] + 1;
				}
				// else if current character of X and Y don't match
				else {
					DP[i][j] = Integer.min(DP[i - 1][j] + 1, DP[i][j - 1] + 1);
				}
			}
		}
		
		// SCS will be last entry in the lookup table
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
	}
	
	public static String printDP(int m , int n , int[][] DP) {
		//TODO: implement it ---easy
		
		return"";
		
	}

	private static String sol_rec(int i1, int i2) {
		
		if(i1==str1.length && i2 < str2.length)
			return new String(Arrays.copyOfRange(str2, i2, str2.length));
			
		if(i2==str2.length && i1 < str1.length)
			return new String(Arrays.copyOfRange(str1, i1, str1.length));
		
		if(i1==str1.length && i2==str2.length )
			return "";
		
		//String str =new String(str1)+new String(str2)+"00";
		
		if(str1[i1]==str2[i2])
			return sol_rec(i1+1,i2+1)+str1[i1];
		
		return minLen( sol_rec(i1+1,i2)+str1[i1], sol_rec(i1,i2+1)+str2[i2]);
	}
	
	
	
	private static String minLen(String... arr) {
		String min = arr[0];
		for (String s : arr)
			min = min.length() > s.length() ? s : min;
		return min;

	}

}
