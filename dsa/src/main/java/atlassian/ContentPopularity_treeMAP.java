package atlassian;

import java.util.*;

class ContentPopularity_treeMAP {
    private Map<Integer, Integer> popularityMap; // Maps contentId to its popularity score
    private TreeMap<Integer, Set<Integer>> popularityRanking; // Tracks contentIds for each popularity score

    public ContentPopularity_treeMAP() {
        popularityMap = new HashMap<>();
        popularityRanking = new TreeMap<>(Collections.reverseOrder()); // Descending order for easy max retrieval
    }

    // Increases popularity score for a given contentId
    public void increasePopularity(int contentId) {
        updatePopularity(contentId, 1);
    }

    // Decreases popularity score for a given contentId
    public void decreasePopularity(int contentId) {
        updatePopularity(contentId, -1);
    }

    // Returns the content ID with the highest popularity
    public int mostPopular() {
        if (popularityRanking.isEmpty()) return -1; // No content exists
        return popularityRanking.firstEntry().getValue().iterator().next(); // Get any most popular contentId
    }

    // Helper function to update popularity score
    private void updatePopularity(int contentId, int delta) {
        int oldScore = popularityMap.getOrDefault(contentId, 0);
        int newScore = oldScore + delta;

        // Remove from old score set
        if (popularityRanking.containsKey(oldScore)) {
            Set<Integer> oldSet = popularityRanking.get(oldScore);
            oldSet.remove(contentId);
            if (oldSet.isEmpty()) popularityRanking.remove(oldScore); // Cleanup empty entries
        }

        // Update score in HashMap
        popularityMap.put(contentId, newScore);

        // Add to new score set
        popularityRanking.computeIfAbsent(newScore, k -> new HashSet<>()).add(contentId);
    }

    public static void main(String[] args) {
        ContentPopularity_treeMAP tracker = new ContentPopularity_treeMAP();
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(1);
        tracker.decreasePopularity(2);
        System.out.println(tracker.mostPopular()); // Should print 1
    }
}


/*
	1.	increasePopularity / decreasePopularity:
	•	HashMap operations: O(1) for retrieving/updating popularity.
	•	TreeSet operations: O(log N) for removal and insertion.
	•	Total Complexity: O(log N).
	2.	mostPopular:
	•	O(1) retrieval using TreeSet.first().
*/