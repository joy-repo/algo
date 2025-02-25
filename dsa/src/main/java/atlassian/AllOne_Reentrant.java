package atlassian;


import java.util.*;
        import java.util.concurrent.locks.*;

class AllOne_Reentrant {
    private final Map<String, Integer> keyCount; // Key -> Frequency
    private final Map<Integer, LinkedHashSet<String>> freqMap; // Frequency -> Keys
    private int minFreq, maxFreq;
    private final ReadWriteLock lock = new ReentrantReadWriteLock(); // Thread-safety

    public AllOne_Reentrant() {
        keyCount = new HashMap<>();
        freqMap = new HashMap<>();
        minFreq = Integer.MAX_VALUE;
        maxFreq = 0;
    }

    public void inc(String key) {
        lock.writeLock().lock(); // Acquire write lock
        try {
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
        } finally {
            lock.writeLock().unlock(); // Release write lock
        }
    }

    public void dec(String key) {
        lock.writeLock().lock(); // Acquire write lock
        try {
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
        } finally {
            lock.writeLock().unlock(); // Release write lock
        }
    }

    public String getMaxKey() {
        lock.readLock().lock(); // Acquire read lock
        try {
            return maxFreq > 0 ? freqMap.get(maxFreq).iterator().next() : "";
        } finally {
            lock.readLock().unlock(); // Release read lock
        }
    }

    public String getMinKey() {
        lock.readLock().lock(); // Acquire read lock
        try {
            return minFreq > 0 ? freqMap.get(minFreq).iterator().next() : "";
        } finally {
            lock.readLock().unlock(); // Release read lock
        }
    }
}