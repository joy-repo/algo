package com.dp;

import java.util.Arrays;

public class LCS_old {

	static String s1 = "AGGTrrAB";
	static String s2 = "GXTXAYB";

	public static void main(String[] args) {
		System.out.println(LCSSol(s1.toCharArray(), s2.toCharArray(), 0, 0,""));

	}

	public static String LCSSol(char[] c1Arr, char[] c2Arr, int m1, int m2, String str) {

		if (m1 == c1Arr.length || m2 == c2Arr.length)
			return str;

		return c1Arr[m1] == c2Arr[m2] ? LCSSol(c1Arr, c2Arr, m1 + 1, m2 + 1, str+c1Arr[m1])
				: getStringWithLargestLength(str,LCSSol(c1Arr, c2Arr, m1, m2 + 1,str), LCSSol(c1Arr, c2Arr, m1+1, m2,str));

	}
	
	private static String getStringWithLargestLength(String... strings) {

		return Arrays.stream(strings).reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2).get();

	}

}
