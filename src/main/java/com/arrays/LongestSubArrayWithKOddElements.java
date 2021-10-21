package com.arrays;

public class LongestSubArrayWithKOddElements {

	static int arr[] = { 3, 4, 6, 1, 9, 8, 2, 10 };
	static int K = 2;

	public static void main(String[] args) {
		solSlidingWindow2();
	}

	/*
	 * public static void solSlidingWindow() { int num = 0; int count = 0; int[]
	 * temp = new int[arr.length]; for (int i = 0; i < arr.length; i++) {
	 * 
	 * temp[num]++; if (arr[i] % 2 != 0) num++; if (num >= K) count = count +
	 * temp[num - K]; } System.out.println(count + temp[num]);
	 * 
	 * }
	 */

	public static void solSlidingWindow2() {

		int[] oddStore = new int[arr.length];
		int start = 0;
		int nOdd = 0;
		int max = 0;

		for (int i = 0; i < arr.length; i++) {

			if (arr[i] % 2 != 0) {
				nOdd++;
				oddStore[nOdd] = i;
			}

			if (nOdd > K && arr[i] % 2 != 0)
				start = oddStore[nOdd - K] + 1;

			// if (nOdd >= K)
			max = Math.max(i - start + 1, max);

		}

		System.out.println(max);

	}

}
