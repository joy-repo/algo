package com.dp;

import java.util.Arrays;

public class LargestPalindrome {

	public static String STR = "forgeeksskeegfor";

	public static void main(String[] args) {
		System.out.println(sol_rec(0, STR.length() - 1));
		sol_DP();
	}

	private static void sol_DP() {

		int[][] DP = new int[STR.length()][STR.length()];

		for (int i = 0; i < STR.length(); i++)
			DP[i][i] = 1;

		for (int d = 1; d < STR.length(); d++) {
			for (int i = 0; i + d < STR.length(); i++) {
				if (STR.charAt(i) == STR.charAt(i + d))
					DP[i][i + d] = DP[i+1][i + d - 1] + 2;
				else
					DP[i][i + d] = Math.max(DP[i+1][i+d],DP[i][i+d-1]);
			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private static int sol_rec(int start, int end) {

		if (start == end)
			return 1;

		if (start > end)
			return 0;

		int res = 0;
		if (STR.charAt(start) == STR.charAt(end))
			res = sol_rec(start + 1, end - 1) + 2;

		return max(sol_rec(start + 1, end), sol_rec(start, end - 1), res);
	}

	private static int max(int... arr) {

		return Arrays.stream(arr).max().getAsInt();
	}
}
