package com.arrays;

///Maximum Sum Path in Two Arrays
public class NC_MaximumSumPath_NC {

	public static int ar1[] = { 2, 3, 7, 10, 12, 15, 30, 34 };
	public static int ar2[] = { 1, 5, 7, 8, 10, 15, 16, 19 };

	public static void main(String[] args) {

		sol();
	}

	private static void sol() {

		int i1 = 0;
		int i2 = 0;
		int sum1 = 0;
		int sum2 = 0;
		int max = 0;
		while (i1 < ar1.length || i2 < ar2.length) {
			
			if(ar1[i1]==ar2[i2]) {
				max = max + Math.max(sum1, sum2);
				sum1 = 0;
				sum2 = 0;
				i1++;
				i2++;
				continue;
			}
			
			if (ar1[i1] < ar2[i2]) {
				sum1 = sum1 + ar1[i1];
				i1++;
				continue;
				
			}

			if (ar1[i1] > ar2[i2]) {
				sum2 = sum2 + ar2[i2];
				i2++;
				continue;
			}

			

		}

		System.out.println(max + Math.max(sum1, sum2));

	}

}
