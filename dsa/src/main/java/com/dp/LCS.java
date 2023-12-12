package com.dp;

import java.util.Arrays;

public class LCS {

	static char[] s1 = "AGGTAB".toCharArray();
	static char[] s2 = "GXTXAYB".toCharArray();
	static String RESULT = "";

	public static void main(String[] args) {
		System.out.println(sol_rec1(0, 0, ""));
		System.out.println(RESULT);
		System.out.println(sol_rec2(0,0));
		sol_DP();
	}

	private static void sol_DP() {
		
		int[][] DP = new int[s1.length+1][s2.length+1];
		
		for(int c=1; c<=s1.length;c++) {
			for(int r=1; r<=s2.length;r++) {
				if(s1[c-1]==s2[r-1])
					DP[c][r]=max(DP[c-1][r-1]+1,DP[c-1][r],DP[c][r-1]);
				else
					DP[c][r]=max(DP[c-1][r],DP[c][r-1]);
			}
		}
		
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
		
	}

	private static int max(int ...arr) {
		
		return Arrays.stream(arr).max().getAsInt();
	}

	private static int sol_rec1(int i1, int i2, String res) {

		if (i1 == s1.length || i2 == s2.length) {
			RESULT = (RESULT.length() > res.length() ? RESULT : res);
			return 0;
		}

		if (s1[i1] == s2[i2])
			return sol_rec1(i1 + 1, i2 + 1, res + s1[i1]) + 1;
		else
			return Math.max(sol_rec1(i1 + 1, i2, res), sol_rec1(i1, i2 + 1, res));
	}

	private static String sol_rec2(int i1, int i2) {
		if (i1 == s1.length || i2 == s2.length)
			return "";
		if (s1[i1] == s2[i2])
			return  s1[i1] +sol_rec2(i1 + 1, i2 + 1);
		else {
			String t1 = sol_rec2(i1 + 1, i2);
			String t2 = sol_rec2(i1, i2 + 1);
			return t1.length() > t2.length() ? t1 : t2;
		}
	}
	
	
}
