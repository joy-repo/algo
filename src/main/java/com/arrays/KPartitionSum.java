package com.arrays;

import java.util.Arrays;

/// Partition of a set into K subsets with equal sum
public class KPartitionSum {

	public static int arr[] = { 2, 1, 4, 5, 3, 3 };
	public static int K = 3;

	public static void main(String[] args) {
		sol_rec1(0, 0, "", 0, ""); // 2-partitions Basic case

		int[] sum = new int[K];
		System.out.println(sol_rec2(0, sum));
	}

	// 2-partitions Basic case
	private static void sol_rec1(int n, int sum1, String str1, int sum2, String str2) {

		if (n == arr.length) {
			if (sum1 == sum2) {
				System.out.println("-------------------------");
				System.out.println("SUM1---" + sum1 + "=" + str1);
				System.out.println("SUM2---" + sum2 + "=" + str2);
				System.out.println("-------------------------");
			}
			return;
		}

		sol_rec1(n + 1, sum1 + arr[n], str1 + "+" + arr[n], sum2, str2);

		sol_rec1(n + 1, sum1, str1, sum2 + arr[n], str2 + "+" + arr[n]);

	}

	/// K partition
	private static boolean sol_rec2(int n, int[] sum) {

		if (n == arr.length)
			return printRes(sum);

		for (int j = 0; j < K; j++) {
			sum[j] += arr[n];

			if (sol_rec2(n + 1, sum))
				return true;
			sum[j] -= arr[n];

		}
		return false;

	}

	private static boolean printRes(int[] sum) {
		int eachSum = Arrays.stream(arr).sum() / K;
		boolean toPrint = true;
		for (int i : sum)
			if (i != eachSum)
				toPrint = false;
		// if(toPrint) System.out.println(toPrint);
		return toPrint;
	}

}
