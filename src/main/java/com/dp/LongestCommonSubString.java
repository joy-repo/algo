package com.dp;

import java.util.Arrays;

public class LongestCommonSubString {

	static char[] s1 = "OldSite:GeeksforGeeks.org".toCharArray();
	static char[] s2 = "NewSite:GeeksQuiz.com".toCharArray();

	public static void main(String[] args) {
		//System.out.println(sol_rec1(0, 0, ""));
		//sol_DP();
		System.out.println(sol_rec1(0, 0, ""));
	}

	private static void sol_DP() {

		int[][] DP = new int[s1.length + 1][s2.length + 1];

		for (int c = 1; c <= s1.length; c++) {
			for (int r = 1; r <= s2.length; r++) {
				if (s1[c - 1] == s2[r - 1])
					DP[c][r] = max(DP[c - 1][r - 1] + 1, DP[c - 1][r], DP[c][r - 1]);
				else
					DP[c][r] = 0;
			}
		}

		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
		int r = Arrays.stream(DP).map(a->Arrays.stream(a).max().getAsInt()).mapToInt(i->(int)i).max().getAsInt();
		System.out.println(r);
	}

	private static int max(int... arr) {

		return Arrays.stream(arr).max().getAsInt();
	}

	private static String sol_rec1(int i1, int i2, String res) {

		if (i1 == s1.length || i2 == s2.length)
			return res;

		String str = "";
		if (s1[i1] == s2[i2])
			str = sol_rec1(i1 + 1, i2 + 1, res + s1[i1]);

		str = maxLen(sol_rec1(i1 + 1, i2, ""), sol_rec1(i1, i2 + 1, ""), str);

		return str;
	}

	private static String maxLen(String... arr) {
		String max = "";
		for (String s : arr)
			max = max.length() > s.length() ? max : s;
		return max;

	}
}
