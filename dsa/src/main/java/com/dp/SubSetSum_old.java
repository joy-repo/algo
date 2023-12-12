package com.dp;

import java.util.stream.IntStream;

public class SubSetSum_old {

	static int arr[] = { 3, 2, 1, 4 };
	static int sum = 6;
	static boolean[][] DP = new boolean[arr.length + 1][sum + 1];

	public static void main(String[] args) {
		// System.out.println(solRecc(0, 0));
		/*
		 * solDP(); System.out.println(DP);
		 */

		/*
		 * String[][] arr_i = new String[5][8];
		 * 
		 * for(int i=0; i<5;i++) for(int j=0;j<8;j++) arr_i[i][j]=""+i+j;
		 * 
		 * for(int i=0; i<5;i++) { System.out.println(); for(int j=0;j<8;j++)
		 * System.out.print(arr_i[i][j]+"-"); } System.out.println();
		 * System.out.println(arr_i[4][5]);
		 */
		System.out.println("-----------------------------");
		System.out.println(solDP());
		print(DP);
	}

	public static boolean solRecc(int l, int tsum) {

		if (l == arr.length)
			return tsum == sum;

		return solRecc(l + 1, tsum + arr[l]) || solRecc(l + 1, tsum);
	}

	public static boolean solDP() {

		// IntStream.range(0, sum+1).forEach(i -> DP[0][i] = true);
		IntStream.range(0, arr.length + 1).forEach(i -> DP[i][0] = true);

		// System.out.println(DP[0][sum]);

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= sum; j++) {
				DP[i][j] = DP[i - 1][j];
				if (arr[i - 1] < j)
					DP[i][j] = DP[i - 1][j] || DP[i - 1][j - arr[i - 1]];
				if (arr[i - 1] == j)
					DP[i][j] = true;
			}

		}

		return DP[arr.length][sum];

	}

	public static  void print(boolean[][] ar) {
		for (int i = 0; i <= arr.length; i++) {
			System.out.println();
			for (int j = 0; j <= sum; j++) {
				System.out.print(ar[i][j]+",");
			}
		}
	}

}
