package com.dp;

public class RodCut_old {
	
	public static int[] cuts = {1,2,3,4};
	public static int[] price = {2,5,7,8};
	public static int len=7;
	
	public static void main(String[] args) {
		int res =sol_rec(0,0);
		System.out.println(res);
	}

	private static int sol_rec(int i, int c) {
		
		if(c>=len)
			return 0;
		int r = 0;
		
		for(int j=0; j<cuts.length; j++) {
			 r = Math.max(sol_rec(j,c+cuts[j])+price[j],r);
		}
		
		return r;
	}

	
	private static void sol_DP() {
		
		int[][] DP = new int[cuts.length+1][len+1];
		
		/*for(int c=0;c<=len;c++)
			DP[c][0]=Integer.MAX_VALUE;*/
		
		for(int c=1;c<=cuts.length;c++) {
			for(int r=1;r<=len;r++) {
				if(cuts[c-1]>r)
					DP[c][r]= DP[c-1][r];
				else
					DP[c][r]=Math.max(DP[c-1][r],DP[c][r-cuts[c-1]]+price[c-1]);
			}
		}
		
	}
	
	private static int getMax(int i, int j) {
		if (i == 0)
			return j;
		if (j == 0)
			return i;
		return Math.max(i, j);
	}
}
