package com.dp;

import java.util.stream.IntStream;

public class EditDistance {

	static char[] chrArr1 = "abcdef".toCharArray();
	static char[] chrArr2 = "azced".toCharArray();
	static int[][] DP = new int[chrArr2.length + 1][chrArr1.length + 1];

	public static void main(String[] args) {
		// System.out.println(solDP());
		System.out.println(solRecc(0,0));
	}

	public static int solRecc(int i1, int i2) {

		if (i1 == chrArr1.length)
			return chrArr2.length - i2;
		if (i2 == chrArr2.length)
			return chrArr1.length - i1;

		if (chrArr1[i1] == chrArr2[i2])
			return solRecc(i1 + 1, i2 + 1);
		else
			return 1 + Math.min(Math.min(solRecc(i1 + 1, i2), solRecc(i1, i2 + 1)), solRecc(i1 + 1, i2 + 1));

	}

	public static int solDP() {

		IntStream.range(0, chrArr1.length + 1).forEach(i -> DP[0][i] = i);
		IntStream.range(0, chrArr2.length + 1).forEach(j -> DP[j][0] = j);

		for (int y = 1; y <= chrArr2.length; y++) {
			for (int x = 1; x <= chrArr1.length; x++) {
				if (chrArr1[x - 1] == chrArr2[y - 1])
					DP[y][x] = DP[y - 1][x - 1];
				else
					DP[y][x] = 1 + Math.min(Math.min(DP[y - 1][x - 1], DP[y][x - 1]), DP[y - 1][x]);
			}
		}

		return DP[chrArr2.length][chrArr1.length];
	}

}
