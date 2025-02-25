package atlassian;

import java.util.*;

class AllOne {
    private Map<String, Integer> keyCount; // Key -> Frequency
    private Map<Integer, LinkedHashSet<String>> freqMap; // Frequency -> Keys
    private int minFreq, maxFreq;

    public AllOne() {
        keyCount = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = Integer.MAX_VALUE;
        maxFreq = 0;
    }

    public void inc(String key) {
        int count = keyCount.getOrDefault(key, 0);
        keyCount.put(key, count + 1);

        // Remove key from current frequency group
        if (count > 0) {
            freqMap.get(count).remove(key);
            if (freqMap.get(count).isEmpty()) {
                freqMap.remove(count);
                if (minFreq == count) minFreq++; // Update minFreq if needed
            }
        }

        // Add key to new frequency group
        freqMap.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);

        // Update max/min frequency
        maxFreq = Math.max(maxFreq, count + 1);
        minFreq = Math.min(minFreq, count == 0 ? count + 1 : minFreq);
    }

    public void dec(String key) {
        if (!keyCount.containsKey(key)) return;

        int count = keyCount.get(key);
        keyCount.put(key, count - 1);

        // Remove key from current frequency group
        freqMap.get(count).remove(key);
        if (freqMap.get(count).isEmpty()) {
            freqMap.remove(count);
            if (maxFreq == count) maxFreq--; // Update maxFreq if needed
            if (minFreq == count) minFreq--; // Update minFreq if needed
        }

        if (count == 1) { // Key is completely removed
            keyCount.remove(key);
        } else {
            // Add key to new frequency group
            freqMap.computeIfAbsent(count - 1, k -> new LinkedHashSet<>()).add(key);
            minFreq = Math.min(minFreq, count - 1);
        }
    }

    public String getMaxKey() {
        return maxFreq > 0 ? freqMap.get(maxFreq).iterator().next() : "";
    }

    public String getMinKey() {
        return minFreq > 0 ? freqMap.get(minFreq).iterator().next() : "";
    }
}
