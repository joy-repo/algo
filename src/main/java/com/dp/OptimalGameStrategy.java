package com.dp;

// Optimal Strategy for a Game | DP-31
public class OptimalGameStrategy {
	static int arr[] = { 20, 30, 2, 2, 2, 10 };

	public static void main(String[] args) {
		System.out.println(sol_rec(0, arr.length - 1, true, "", 0));
		sol_DP();
	}

	private static void sol_DP() {
		
		
	}

	private static int sol_rec(int start, int end, boolean isP1, String res, int s) {

		if (end < start) {
			System.out.println(res + "=" + s);
			return 0;
		}

		if (isP1) {
			return Math.max(
					sol_rec(start + 1, end, !isP1, res + "+" + arr[start] + "(" + start + ")", s + arr[start])
							+ arr[start],
					sol_rec(start, end - 1, !isP1, res + "+" + arr[end] + "(" + end + ")", s + arr[end]) + arr[end]);
		} else {
			return Math.max(sol_rec(start + 1, end, !isP1, res, s), sol_rec(start, end - 1, !isP1, res, s));
		}

	}
	
	

}
