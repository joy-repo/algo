package com.dp;

import java.util.Arrays;

public class KnapSack {

	public static int[] WEIGHT = { 1, 3, 4, 5 };
	public static int[] VAL = { 1, 4, 5, 7 };
	public static int MAX_WT = 7;

	public static void main(String[] args) {
		 System.out.println(sol_rec(0, 0));
		 sol_DP1();
		 System.out.println("-----------DP2-----------");
		sol_DP2();

	}

	private static void sol_DP1() {
		int[][] DP = new int[VAL.length + 1][MAX_WT + 1];

		for (int i = 0; i <= VAL.length; i++)
			for (int j = 0; j <= MAX_WT; j++) {
				if (i == 0 || j == 0) {
					DP[i][j] = 0;
					continue;
				}
				if (WEIGHT[i - 1] <= j)
					DP[i][j] = Math.max(DP[i - 1][j - WEIGHT[i - 1]] + VAL[i - 1], DP[i - 1][j]);
				else
					DP[i][j] = DP[i - 1][j];
			}

		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	private static void sol_DP2() {
		int[] DP = new int[MAX_WT + 1];

		for (int i = 0; i < VAL.length; i++)
			for (int j = 0; j <= MAX_WT; j++)

				if (WEIGHT[i] <= j)
					DP[j] = Math.max(DP[j - WEIGHT[i]] + VAL[i], DP[j]);

		System.out.println(Arrays.toString(DP));
	}

	private static int sol_rec(int i, int wt) {

		if (wt >= MAX_WT)
			return 0;
		if (i == VAL.length)
			return 0;

		if (MAX_WT < WEIGHT[i] + wt)
			return sol_rec(i + 1, wt);
		else
			return Math.max(sol_rec(i + 1, wt + WEIGHT[i]) + VAL[i], sol_rec(i + 1, wt));
	}

}
