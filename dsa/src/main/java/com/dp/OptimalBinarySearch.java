package com.dp;

import java.util.stream.IntStream;

public class OptimalBinarySearch {

	static int input[] = { 10, 12, 16, 21 };
	static int freq[] = { 4, 2, 6, 3 };
	static int[][] DP = new int[input.length][input.length];

	public static void main(String[] args) {
		solDP();
		printDP();
	}

	private static void printDP() {
		for (int y = 0; y < input.length; y++) {
			System.out.println();
			for (int x = 0; x < input.length; x++)
				System.out.print(DP[y][x] + "--");
		}

	}

	public static void solDP() {

		for (int i = 0; i < input.length; i++)
			DP[i][i] = freq[i];

		for (int y = 0; y < input.length; y++) {
			for (int x = y + 1; x < input.length; x++) {
				int sum = IntStream.range(y, x + 1).map(i -> freq[i]).sum();
				int min = Integer.MAX_VALUE;
				for (int k = y; k < x; k++) {
					int val = sum + (k ==y ? 0 : DP[y][k - 1]) + (k == x ? 0 : DP[k + 1][x]);
					min = val < min ? val : min;
				}
				DP[y][x] = min;
			}
		}

	}

}
