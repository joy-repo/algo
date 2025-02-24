package atlassian;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;


public class AllinOneDS_multiThreaded {
    private final ConcurrentHashMap<String, Integer> valueCount; // Stores frequency of elements
    private final ConcurrentSkipListMap<Integer, LinkedHashSet<String>> freqMap; // Frequency -> Set of elements
    private final ConcurrentLinkedDeque<String> orderedElements; // Maintains insertion order
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(); // Ensures thread safety

    public AllinOneDS_multiThreaded() {
        valueCount = new ConcurrentHashMap<>();
        freqMap = new ConcurrentSkipListMap<>(Collections.reverseOrder()); // High to low frequency
        orderedElements = new ConcurrentLinkedDeque<>();
    }

    /** Add a string (or increase frequency) in O(log N) */
    public void add(String value) {
        lock.writeLock().lock();
        try {
            int oldCount = valueCount.getOrDefault(value, 0);
            int newCount = oldCount + 1;

            valueCount.put(value, newCount);

            // Remove from old frequency mapping if exists
            if (oldCount > 0) {
                freqMap.get(oldCount).remove(value);
                if (freqMap.get(oldCount).isEmpty()) {
                    freqMap.remove(oldCount);
                }
            }

            // Add to new frequency mapping
            freqMap.computeIfAbsent(newCount, k -> new LinkedHashSet<>()).add(value);

            // Maintain insertion order
            if (oldCount == 0) orderedElements.addLast(value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    /** Remove or decrease frequency in O(log N) */
    public void remove(String value) {
        lock.writeLock().lock();
        try {
            if (!valueCount.containsKey(value)) return;

            int oldCount = valueCount.get(value);
            int newCount = oldCount - 1;

            // Remove from current frequency mapping
            freqMap.get(oldCount).remove(value);
            if (freqMap.get(oldCount).isEmpty()) {
                freqMap.remove(oldCount);
            }

            if (newCount == 0) {
                valueCount.remove(value);
                orderedElements.remove(value); // Remove completely if count reaches 0
            } else {
                valueCount.put(value, newCount);
                freqMap.computeIfAbsent(newCount, k -> new LinkedHashSet<>()).add(value);
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    /** Get Most Frequent String in O(1) */
    public String getMostFrequent() {
        lock.readLock().lock();
        try {
            if (freqMap.isEmpty()) return null;
            return freqMap.firstEntry().getValue().iterator().next();
        } finally {
            lock.readLock().unlock();
        }
    }

    /** Get Least Frequent String in O(1) */
    public String getLeastFrequent() {
        lock.readLock().lock();
        try {
            if (freqMap.isEmpty()) return null;
            return freqMap.lastEntry().getValue().iterator().next();
        } finally {
            lock.readLock().unlock();
        }
    }

    /** Get Top K Most Frequent Strings in O(K log N) */
    public List<String> getTopK(int k) {
        lock.readLock().lock();
        try {
            List<String> result = new ArrayList<>();
            for (Map.Entry<Integer, LinkedHashSet<String>> entry : freqMap.entrySet()) {
                for (String value : entry.getValue()) {
                    result.add(value);
                    if (result.size() == k) return result;
                }
            }
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }

    /** Get elements in insertion order in O(N) */
    public List<String> getInsertionOrder() {
        lock.readLock().lock();
        try {
            return new ArrayList<>(orderedElements);
        } finally {
            lock.readLock().unlock();
        }
    }
}