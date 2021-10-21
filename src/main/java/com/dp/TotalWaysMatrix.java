package com.dp;

import java.util.Arrays;

// counting path
public class TotalWaysMatrix {

	static int[][] Mat = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
	static int[][] DP = new int[Mat.length][Mat[0].length];

	public static void main(String[] args) {
		System.out.println(sol_rec(0, 0));
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	private static int sol_rec1(int c, int r) {

		if (c == Mat.length && r == Mat[0].length)
			return 0;

		if (c == Mat.length)
			return sol_rec(c, r + 1) + Mat[c - 1][r];

		if (r == Mat[0].length)
			return sol_rec(c + 1, r) + Mat[c][r - 1];

		return Math.min(sol_rec(c, r + 1), sol_rec(c + 1, r)) + Mat[c][r];

	}
	// with DP bottom up
	private static int sol_rec(int c, int r) {

		if (c == Mat.length && r == Mat[0].length)
			return 0;

		if (c == Mat.length)
			return sol_rec(c, r + 1) + Mat[c - 1][r];

		if (r == Mat[0].length)
			return sol_rec(c + 1, r) + Mat[c][r - 1];

		if (DP[c][r] == 0) {

			DP[c][r] = Math.min(sol_rec(c, r + 1), sol_rec(c + 1, r)) + Mat[c][r];
		}

		return DP[c][r];
	}
}
