package com.arrays;

import java.util.Arrays;

// Maximum profit earned by buying and selling shares any number of times
//Maximum profit earned by buying and selling shares K number of times
public class ProfitByShares {

	public static int[] iArr = { 12, 14, 17, 10, 14, 13, 12, 15 };
	static int K = 2;

	public static void main(String[] args) {
		sol();
		sol1();
		sol2();
	}

	// Maximum profit earned by buying and selling shares any number of times
	public static void sol() {

		int profit = 0;
		/* int start = 0; */

		for (int i = 1; i < iArr.length; i++) {

			/*
			 * if(iArr[start]<iArr[i] && i == iArr.length-1) profit =
			 * profit+iArr[i]-iArr[start];
			 */

			if (iArr[i] > iArr[i - 1])
				profit = profit + iArr[i] - iArr[i - 1];

			/*
			 * profit = profit + iArr[i-1] - iArr[start]; start = i;
			 */
		}

		System.out.println(profit);
	}

	/// when k=1
	public static void sol1() {
		int[] maxArr = new int[iArr.length];
		int len = iArr.length - 1;
		maxArr[len] = iArr[len];

		for (int j = len - 1; j >= 0; j--)
			if (iArr[j] >= maxArr[j + 1])
				maxArr[j] = iArr[j];
			else
				maxArr[j] = maxArr[j + 1];

		int res = 0;
		for (int i = 0; i <= len; i++)
			res = Math.max(res, maxArr[i] - iArr[i]);
		System.out.println(res);

	}
	
	/// when k=K
		public static void sol2() {
			int[] maxArr = new int[iArr.length];
			int len = iArr.length - 1;
			maxArr[len] = iArr[len];

			for (int j = len - 1; j >= 0; j--)
				if (iArr[j] >= maxArr[j + 1])
					maxArr[j] = iArr[j];
				else
					maxArr[j] = maxArr[j + 1];

			int[] res = new int[iArr.length];
			for (int i = 0; i <= len; i++)
				res[i] = maxArr[i] - iArr[i];
			
			Arrays.sort(res);
			
			int sum =0;
			
			for(int k=0; k<K;k++)
				sum=sum+res[len-k];
			
			System.out.println(sum);

		}

}
