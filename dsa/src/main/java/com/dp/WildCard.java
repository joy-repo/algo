package com.dp;

public class WildCard {

	static char[] str = "xaylmz".toCharArray();
	static char[] pattern = "x?y*z".toCharArray();

	public static boolean solRecc(int i, int j) {

		if (i == str.length && j == pattern.length)
			return true;
		if (j == str.length && j < pattern.length)
			return false;
		if (j < str.length && j == pattern.length)
			return false;

		if (pattern[j - 1] == '.' && pattern[j] == '*')
			return true;

		if (str[i] == pattern[j])
			return solRecc(i + 1, j + 1);
		if (pattern[j] == '*' && pattern[j - 1] == str[i])
			return solRecc(i + 1, j);
		if (pattern[j] == '.')
			return solRecc(i + 1, j + 1);
		return false;

	}

	public static boolean solDP() {
		boolean DP[][] = new boolean[pattern.length + 1][str.length + 1];
		DP[0][0] = true;
		for (int y = 1; y <= pattern.length; y++) {
			for (int x = 1; x <= str.length; x++) {
				if (str[x - 1] == pattern[y - 1] || pattern[y - 1] == '.')
					DP[y][x] = DP[y - 1][x - 1];
				if (pattern[y - 1] == '*') {
					if (pattern[y - 2] == '.' || pattern[y - 2] == str[x - 1])
						DP[x][y] = DP[y - 1][x];
				}
			}
		}
		return DP[pattern.length][str.length];
	}

}
