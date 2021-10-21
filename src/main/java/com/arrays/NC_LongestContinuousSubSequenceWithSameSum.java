package com.arrays;

import java.util.HashMap;
import java.util.Map;

//Length of longest continuous sequence with same sum in given binary arrays

public class NC_LongestContinuousSubSequenceWithSameSum {

	public static int[] arr1 = { 0, 0, 1, 1, 1, 1 };
	public static int[] arr2 = { 1, 1, 0, 1, 0, 1 };

	public static void main(String[] args) {
		sol();
	}

	private static void sol() {

		Map<Integer, Integer> diffMap = new HashMap<>();
		diffMap.put(0, 0);
		int sum1 = 0;
		int sum2 = 0;
		int res = 0;
		for (int j = 0, i = 0; i <= arr1.length || j < arr2.length; i++, j++) {

			/*
			 * if (i >= arr1.length - 1) j = arr1.length - 1; if (j >= arr2.length - 1) j =
			 * arr2.length - 1;
			 */

			sum1 += arr1[i];
			sum2 += arr2[j];

			if (!diffMap.containsKey(sum2 - sum1))
				diffMap.put(sum2 - sum1, i + 1);

			if (diffMap.containsKey(sum1 - sum2)) {
				res = Math.max(res, i - diffMap.get(sum1 - sum2));
			}

		}
		System.out.println(res);

	}

}
