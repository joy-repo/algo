package atlassian;

import java.util.*;
import java.util.concurrent.*;

class AllOne_minimumThreadContention {
    private final ConcurrentHashMap<String, Integer> keyCount; // Key -> Frequency
    private final ConcurrentSkipListMap<Integer, LinkedHashSet<String>> freqMap; // Frequency -> Keys
    private final Object writeLock; // Single object lock for modifying freqMap safely

    public AllOne_minimumThreadContention() {
        keyCount = new ConcurrentHashMap<>();
        freqMap = new ConcurrentSkipListMap<>();
        writeLock = new Object(); // Lightweight lock for frequency updates
    }

    public void inc(String key) {
        keyCount.compute(key, (k, count) -> {
            int newCount = (count == null) ? 1 : count + 1;

            synchronized (writeLock) {
                if (count != null) {
                    freqMap.get(count).remove(k);
                    if (freqMap.get(count).isEmpty()) {
                        freqMap.remove(count);
                    }
                }
                freqMap.computeIfAbsent(newCount, x -> new LinkedHashSet<>()).add(k);
            }
            return newCount;
        });
    }

    public void dec(String key) {
        keyCount.computeIfPresent(key, (k, count) -> {
            int newCount = count - 1;

            synchronized (writeLock) {
                freqMap.get(count).remove(k);
                if (freqMap.get(count).isEmpty()) {
                    freqMap.remove(count);
                }
                if (newCount > 0) {
                    freqMap.computeIfAbsent(newCount, x -> new LinkedHashSet<>()).add(k);
                }
            }
            return (newCount > 0) ? newCount : null; // Remove key if count reaches zero
        });
    }

    public String getMaxKey() {
        if (freqMap.isEmpty()) return "";
        return freqMap.lastEntry().getValue().iterator().next();
    }

    public String getMinKey() {
        if (freqMap.isEmpty()) return "";
        return freqMap.firstEntry().getValue().iterator().next();
    }
}