package com.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetSum {

	static int arr[] = { 2, 3, 7, 8, 10 };
	static int SUM = 11;

	public static void main(String[] args) {
		System.out.println(sol_Rec1(0, 0));
		sol_Rec2(0, 0, new ArrayList<>());
		sol_DP();
	}

	private static void sol_DP() {

		boolean[][] DP = new boolean[arr.length + 1][SUM + 1];

		/*
		 * for (int c = 0; c <= arr.length; c++) { for (int r = 0; r <= SUM; r++) { if
		 * (r == 0) DP[c][r] = true; } }
		 */

		for (int c = 0; c <= arr.length; c++) {
			DP[c][0] = true;
		}

		for (int c = 1; c <= arr.length; c++) {
			for (int r = 0; r <= SUM; r++) {

				// if (r == 0) { DP[c][r] = true; continue; }

				if (arr[c - 1] > r)
					DP[c][r] = DP[c - 1][r];
				else
					DP[c][r] = DP[c - 1][r] || DP[c - 1][r - arr[c - 1]];

			}
		}
		Arrays.stream(DP).forEach(a -> System.out.println(Arrays.toString(a)));

	}

	private static boolean sol_Rec1(int i, int s) {

		if (s == SUM)
			return true;

		if (i == arr.length) {
			return false;
		}

		return sol_Rec1(i + 1, s + arr[i]) || sol_Rec1(i + 1, s);

	}

	private static void sol_Rec2(int i, int s, List<Integer> res) {

		if (s == SUM)
			System.out.println(res);

		if (i == arr.length)
			return;

		res.add(arr[i]);
		sol_Rec2(i + 1, s + arr[i], res);
		res.remove(res.size() - 1);
		sol_Rec2(i + 1, s, res);

	}

	/// TODO: using Combinations[for loops]
	private static void sol_Rec3(int i, int s, List<Integer> res) {

		if (s == SUM)
			System.out.println(res);

		if (i == arr.length)
			return;

		res.add(arr[i]);
		sol_Rec3(i + 1, s + arr[i], res);
		res.remove(res.size() - 1);
		sol_Rec3(i + 1, s, res);

	}

}
