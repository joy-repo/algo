package com.arrays;

import java.util.HashMap;
import java.util.Map;

public class TripletSumInArray {

	public static int[] arr = { 2, 7, 4, 0, 9, 5, 1, 3 };
	public static int SUM = 6;
	public static int NUM = 3; // Number of elements for which sum will be valid

	public static void main(String[] args) {
		System.out.println(sol_rec(0, 0, 0));
		System.out.println("-------------------------");
		sol_rec1(0, 0, 0, "");
		System.out.println("-------------------------");
		sol_DP();
		System.out.println("-------------------------");
		sol_DP1();
	}

	// returns True/False -- resolve while returning
	private static boolean sol_rec(int i, int j, int s) {

		if (i == arr.length)
			return (s == SUM && j == NUM) ? true : false;

		if (j == NUM)
			return s == SUM ? true : false;

		return (sol_rec(i + 1, j, s) || sol_rec(i + 1, j + 1, s + arr[i]));

	}

	// Returns Void - No Processing while Returning
	// Prints all possible combinations
	private static void sol_rec1(int i, int j, int s, String res) {

		if (i == arr.length) {
			if (s == SUM && j == NUM)
				System.out.println(res + "=" + SUM);
			return;
		}

		if (j == NUM) {
			if (s == SUM)
				System.out.println(res + "=" + SUM);
			return;
		}

		sol_rec1(i + 1, j, s, res);
		res = res.equals("") ? res : res + "+";
		sol_rec1(i + 1, j + 1, arr[i] + s, res + arr[i]);

	}

	private static void sol_DP() {

		for (int n1 = 0; n1 < arr.length - 2; n1++)
			for (int n2 = n1 + 1; n2 < arr.length - 1; n2++)
				for (int n3 = n2 + 1; n3 < arr.length; n3++)
					if (arr[n1] + arr[n2] + arr[n3] == SUM)
						System.out.println(arr[n1] + "+" + arr[n2] + "+" + arr[n3] + "=" + SUM);

	}

	private static void sol_DP1() {

		Map<Integer, Integer> DP = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum = sum + arr[i];
			DP.put(arr[i], i);
		}

		for (int n1 = 0; n1 < arr.length - 2; n1++)
			for (int n2 = n1 + 1; n2 < arr.length - 1; n2++) {
				int diff = SUM-arr[n1]-arr[n2];
				if(DP.containsKey(diff)) {
					int i = DP.get(diff);
					if(i!=n1 && i!=n2)
						System.out.println(arr[n1] + "+" + arr[n2] + "+" +arr[i] + "=" + SUM);
				}
			}
				

	}

}
