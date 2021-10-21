package com.arrays;

public class NC_QuickSelect {
	
	static int[] arr = new int[]{10, 4, 5, 8, 6, 11, 26}; 
	static int K=3;
	
	public static void main(String[] args) {
		sol(0,arr.length-1);
	}

	private static void sol(int low, int high) {
		
		if(low<high) {
			int p = partition (low,high);
			
			sol(low,p-1);
			sol(p+1,high);
		}
		
	}

	private static int partition(int low, int high) {
		
		int pivot = arr[high];
		int i = low;
		for (int j = low+1; j <= high- 1; j++) {
			
		}
		
		return 0;
	}

}
