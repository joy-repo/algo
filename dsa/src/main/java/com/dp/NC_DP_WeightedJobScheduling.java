package com.dp;

public class NC_DP_WeightedJobScheduling {
	
	public static int[] start= {1,3,6,2};
	public static int[] finish= {2,5,19,100};
	public static int[] profit= {50,20,100,200};
	
	
	public static void main(String[] args) {
		
		System.out.println(sol_rec(2,0));
		sol_DP();
		
	}


	private static void sol_DP() {
		
		
	}


	private static int sol_rec( int fn ,int i) {
		
		if(i==start.length)
			return 0;
		
		int res =0;
		
		if(fn<start[i])
			res =sol_rec(finish[i],i+1)+profit[i];
		
		return Math.max(res, sol_rec(fn,i+1));
		
	}
}
