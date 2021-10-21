package com.dp;

public class LongestPalindromicSubsequence {

	static char[] str = "agbdba".toCharArray();
	static int[][] DP = new int[str.length][str.length];

	public static int solRecc(int s, int e) {

		if (e - s <= 0)
			return 0;

		if (str[s] == str[e])
			return solRecc(s + 1, e - 1);
		else
			return Math.max(solRecc(s + 1, e), solRecc(s, e - 1));

	}

	public static void SolDP() {

		for (int i = 0; i < str.length; i++)
			DP[i][i] = 1;

		for (int y = 0; y < str.length; y++) {
			for (int x = y + 1; x < str.length; x++) {
				if (str[x] == str[y]) {
					DP[y][x] = DP[y + 1][x - 1] + 2;
				} else {
					DP[y][x] = Math.max(DP[y][x - 1], DP[y + 1][x]);
				}
			}
		}

	}

}
