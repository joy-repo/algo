package com.arrays;

public class BayerMoore {

	// Function to return majority element present in given array
	public static int majorityElement(int[] A) {
		// m stores majority element if present
		int m = -1;

		// initialize counter i with 0
		int i = 0;

		// do for each element A[j] of the array
		for (int j = 0; j < A.length; j++) {
			// if the counter i becomes 0
			if (i == 0) {
				// set the current candidate to A[j]
				m = A[j];

				// reset the counter to 1
				i = 1;
			}

			// else increment the counter if A[j] is current candidate
			else if (m == A[j]) {
				i++;
			}
			// else decrement the counter if A[j] is not current candidate
			else {
				i--;
			}
		}

		return m;
	}

	// main function
	public static void main(String[] args) {
		// Assumption - valid input (majority element is present)
		int[] arr = { 2, 2,3,2,4,2,5,2,6};

		System.out.println("Majority element is " + majorityElement(arr));
	}
}
