package com.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordBreak_old {

	static String STR = "IAMGOOD";
	static Set<String> dic = new HashSet<>();

	public static void main(String[] args) {

		dic.add("I");
		dic.add("AM");
		dic.add("A");
		dic.add("GOOD");
		dic.add("BOY");
		// System.out.println(sol_rec(5, STR.length()));
		sol_DP();
	}

	private static void sol_DP() {

		boolean[][] DP = new boolean[STR.length() + 1][STR.length() + 1];

		for (int r = 1; r <= STR.length(); r++)
			DP[r][r] = dic.contains(STR.charAt(r - 1) + "");

		for (int d = 1; d <= STR.length(); d++) {
			for (int r = 1; r + d <= STR.length(); r++) {
				if (dic.contains(STR.substring(r - 1, r + d))) {
					DP[r][r + d] = true;
				} else {

					for (int k = r; k <= r + d; k++) {
						if (DP[r][k] && DP[k + 1][r + d - k - 1]) {
							DP[r][r + d] = true;
							break;
						}

					}
				}

			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private static boolean sol_rec(int start, int end) {

		if (start >= end)
			return true;
		if (start <= 0)
			return false;
		if (end == STR.length())
			return false;

		for (int i = start; i <= end; i++) {
			System.out.println(start + "---------" + (i + 1));
			if (dic.contains(STR.substring(start, i + 1))) {
				return sol_rec(0, i - 1) && sol_rec(i + 1, STR.length());
			}
		}
		return sol_rec(start - 1, end) && sol_rec(start, end + 1);

	}
}
