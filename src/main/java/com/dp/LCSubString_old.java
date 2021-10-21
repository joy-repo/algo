package com.dp;

import java.util.Arrays;

public class LCSubString_old {

	static char[] c1Arr = "ZGeeRee99".toCharArray();

	static char[] c2Arr = "AGeeree99Q78".toCharArray();

	public static void main(String[] args) {
		System.out.println(SolRecurr(0, 0,""));
	}

	public static String SolDP(char[] c1Arr, char[] c2Arr) {

		return "";
	}
	
	
	/*
	 * public static int SolRecurrN(int m1, int m2, int res) {
	 * 
	 * if (m1 == c1Arr.length || m2 == c2Arr.length) return res;
	 * 
	 * 
	 * return c1Arr[m1] == c2Arr[m2] ? SolRecurrN(m1 + 1, m2 + 1, res+1) :
	 * Math.max(SolRecurrN(m1 + 1, m2,0), SolRecurrN(m1, m2 + 1,0)); }
	 */

	public static String SolRecurr(int m1, int m2, String res) {

		if (m1 == c1Arr.length || m2 == c2Arr.length) return res;
			

		return c1Arr[m1] == c2Arr[m2] ? SolRecurr(m1 + 1, m2 + 1, res+c1Arr[m1])
				: getStringWithLargestLength(SolRecurr(m1 + 1, m2,""), SolRecurr(m1, m2 + 1,""));
	}

	private static String getStringWithLargestLength(String... strings) {

		return Arrays.stream(strings).reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).get();

	}

}
