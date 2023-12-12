package com.dp;

//public class CoinChanging_old {
//
//	static int arr[] = { 1, 5, 6, 8,3 };
//	static int sum = 4;
//	static int[][] DP = new int[arr.length + 1][sum + 1];
//
//	public static void main(String[] args) {
//		System.out.println(solRecc(0, 0, Integer.MAX_VALUE, ""));
//
//	}
//
//	public static int solRecc(int coins, int tSum, int res, String ss) {
//
//		if (sum < tSum)
//			return Integer.MAX_VALUE;
//		if (sum == tSum) {
//			System.out.println(ss);
//			return coins < res ? coins : res;
//		}
//
//		for (int v : arr) {
//			res = Math.min(solRecc(coins + 1, tSum + v, res, ss + v), res);
//
//		}
//		return res;
//
//	}
//
//	public static void SolDP() {
//
//		for(int i=1; i<=sum;i++) {
//			for(int j=1;j<=arr.length;j++) {
//
//			}
//		}
//	}
//
//}
