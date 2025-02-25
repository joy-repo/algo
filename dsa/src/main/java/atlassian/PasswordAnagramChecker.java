package atlassian;



import java.util.*;

public class PasswordAnagramChecker {
    public static int[] findLastAnagramIndices(String[] passwordArr1, String[] passwordArr2) {
        Map<String, Integer> lastOccurrenceMap = new HashMap<>();

        // Step 1: Process passwordArr1 and store last occurrence of each anagram signature
        for (int i = 0; i < passwordArr1.length; i++) {
            String signature = getSignature(passwordArr1[i]);
            lastOccurrenceMap.put(signature, i); // Store last occurrence index
        }

        // Step 2: Query passwordArr2 to find the last occurrence of an anagram
        int[] result = new int[passwordArr2.length];
        for (int i = 0; i < passwordArr2.length; i++) {
            String signature = getSignature(passwordArr2[i]);
            result[i] = lastOccurrenceMap.getOrDefault(signature, -1); // -1 if not found
        }

        return result;
    }

    // Helper method to generate frequency signature of a string (O(1) time)
    private static String getSignature(String s) {
        int[] freq = new int[26]; // Only lowercase letters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        return Arrays.toString(freq); // Convert frequency array to a unique key
    }

    public static void main(String[] args) {
        String[] passwordArr1 = {"polo", "neal", "nodl"};
        String[] passwordArr2 = {"lean", "ldon", "loop"};

        int[] result = findLastAnagramIndices(passwordArr1, passwordArr2);
        System.out.println(Arrays.toString(result)); // Output: [1, 2, 0]
    }
}