package com.dp;

import java.util.Arrays;

public class CoinChangingMinimum {

	public static int[] arr = {1, 5, 6, 8};
	public static int SUM = 11;

	public static int RES = Integer.MAX_VALUE;

	public static void main(String[] args) {
		sol_DP();
		System.out.println(sol_rec(0, 0));
		
	}

	private static int sol_rec(int i, int s) {

		if (s > SUM)
			return Integer.MAX_VALUE;

		if (s == SUM) 
			return i;
		
		int r=Integer.MAX_VALUE;
		
		for (int k = 0; k < arr.length; k++)
			r = Math.min(sol_rec(i + 1, s + arr[k]),r);
		return r;
	}

	private static void sol_DP() {

		int[][] DP = new int[arr.length + 1][SUM + 1];

		for (int c = 1; c <= arr.length; c++) {
			for (int r = 1; r <= SUM; r++) {
				if (arr[c - 1] > r)
					DP[c][r] = DP[c - 1][r];
				else
					DP[c][r] = Math.min(DP[c - 1][r], DP[c][r - arr[c - 1]] + 1);

			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));
	}

	private static int getVal(int i, int j) {
		if (i == 0)
			return j;
		if (j == 0)
			return i;
		return Math.min(i, j);
	}

}
