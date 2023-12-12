package com.dp;

import java.util.Arrays;

public class NC_EggDropping {

	static int E = 2, F = 10;

	public static void main(String[] args) {
		//System.out.println(sol_rec(-1, E, false));
		sol_DP();
	}

	private static int sol_rec(int floor, int eggs, boolean isBroken) {

		if (floor == F)
			return 1;
		if (eggs == 1)
			return F - floor;

		int res = 0, i_flr = 0, f_flr = F, min = Integer.MAX_VALUE;
		if (isBroken)
			f_flr = floor - 1;
		else
			i_flr = floor + 1;

		for (int i = i_flr; i < f_flr; i++) {
			res = 1 + max(sol_rec(i, eggs, false), sol_rec(i, eggs - 1, true));

		}
		if (res < min)
			min = res;
		return min;
	}

	public static void sol_DP() {

		int[][] DP = new int[E + 1][F + 1];

		for (int r = 0; r <= F; r++) {
			DP[1][r] = r;
		}

		for (int c = 2; c <= E; c++) {
			for (int r = 1; r < F; r++) {
				if (r < c)
					DP[c][r] = DP[c - 1][r];
				else {
					DP[c][r] = Integer.MAX_VALUE;
					for (int k = 1; k <= r; k++) {
						int min = Math.max(DP[c][r - k], DP[c - 1][k - 1])+1;
						if (min < DP[c][r])
							DP[c][r] = min;
					}
				}
			}
		}

		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private static int max(int... arr) {

		return Arrays.stream(arr).max().getAsInt();
	}

}
