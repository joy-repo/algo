package com.dp;

import java.util.Arrays;

public class RegularExpressionMatching_old {

	public static char[] PATTERN = "xa*b.c".toCharArray();
	public static char[] STR = "xaabyc".toCharArray();

	public static void main(String[] args) {
		System.out.println(sol_rec(0, 0));
		sol_DP();
	}

	private static void sol_DP() {

		boolean[][] DP = new boolean[STR.length + 1][PATTERN.length + 1];
		DP[0][0] = true;

		for (int c = 1; c <= STR.length; c++) {
			for (int r = 1; r <= PATTERN.length; r++) {
				
				if (STR[c - 1] == PATTERN[r - 1] && DP[c-1][r-1])
					DP[c][r] = true;
				if (PATTERN[r - 1] == '.'  && DP[c-1][r-1])
					DP[c][r] = true;
				if (PATTERN[r - 1] == '*')
					DP[c][r] = (STR[c - 1] == PATTERN[r - 2] && DP[c-1][r-1])|| DP[c][r - 2];

			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private static boolean sol_rec(int p, int s) {

		if (p == PATTERN.length && STR.length == s)
			return true;

		if (PATTERN[p] == '*') {
			if (PATTERN[p - 1] == '.')
				return true;
			if (PATTERN[p - 1] == STR[s])
				return sol_rec(p, s + 1);
			else
				return sol_rec(p + 1, s);
		}
		if (PATTERN[p] == '.')
			return sol_rec(p + 1, s + 1);

		if (PATTERN[p] == STR[s])
			return sol_rec(p + 1, s + 1);

		return false;

	}
}
