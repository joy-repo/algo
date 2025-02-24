package atlassian;

import java.util.*;

public class FindKClosestElement {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;

        // Binary search to find the closest element
        while (right - left >= k) {
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                right--;
            } else {
                left++;
            }
        }

        // Collect k closest elements
        List<Integer> result = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        FindKClosestElement solver = new FindKClosestElement();
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;
        System.out.println(solver.findClosestElements(arr, k, x)); // Output: [1, 2, 3, 4]
    }
}
