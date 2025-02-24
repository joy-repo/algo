package atlassian;

import java.util.*;





class AllInOneDS {
    private Map<String, Integer> valueCount;   // Stores frequency of elements
    private TreeMap<Integer, LinkedHashSet<String>> freqMap; // Frequency -> Set of elements
    private Deque<String> orderedElements; // Maintains insertion order

    public AllInOneDS() {
        valueCount = new HashMap<>();
        freqMap = new TreeMap<>(Collections.reverseOrder()); // High to low frequency
        orderedElements = new LinkedList<>();
    }

    /** Add a string (or increase frequency) in O(log N) */
    public void add(String value) {
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
    }

    /** Remove or decrease frequency in O(log N) */
    public void remove(String value) {
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
    }

    /** Get Most Frequent String in O(1) */
    public String getMostFrequent() {
        if (freqMap.isEmpty()) return null;
        return freqMap.firstEntry().getValue().iterator().next();
    }

    /** Get Least Frequent String in O(1) */
    public String getLeastFrequent() {
        if (freqMap.isEmpty()) return null;
        return freqMap.lastEntry().getValue().iterator().next();
    }

    /** Get Top K Most Frequent Strings in O(K log N) */
    public List<String> getTopK(int k) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<Integer, LinkedHashSet<String>> entry : freqMap.entrySet()) {
            for (String value : entry.getValue()) {
                result.add(value);
                if (result.size() == k) return result;
            }
        }
        return result;
    }

    /** Get elements in insertion order in O(N) */
    public List<String> getInsertionOrder() {
        return new ArrayList<>(orderedElements);
    }
}