package atlassian;

import java.util.*;

class ContentPopularity_DLL {
    private class Node {
        int contentId;
        int popularityScore;
        Node prev, next;

        Node(int contentId, int popularity) {
            this.contentId = contentId;
            this.popularityScore = popularity;
        }
    }

    private Map<Integer, Node> contentMap; // Maps contentId → Node
    private Map<Integer, LinkedHashSet<Integer>> bucketMap; // Maps popularity → Set of contentIds
    // LinkedHashSet instead of Set to maintain the order of the contentIds for each bucket.
    //Discuss if it is really needed as tie-breaker or something else.
    private int maxPopularity; // Keeps track of highest popularity

    public ContentPopularity_DLL() {
        contentMap = new HashMap<>();
        bucketMap = new HashMap<>();
        maxPopularity = 0;
    }

    public void increasePopularity(int contentId) {
        updatePopularity(contentId, 1);
    }

    public void decreasePopularity(int contentId) {
        updatePopularity(contentId, -1);
    }

    public int mostPopular() {
        if (bucketMap.isEmpty()) return -1;
        return bucketMap.get(maxPopularity).iterator().next(); // O(1) retrieval
    }

    private void updatePopularity(int contentId, int delta) {
        int oldScore = contentMap.getOrDefault(contentId, new Node(contentId, 0)).popularityScore;
        int newScore = oldScore + delta;

        // Remove contentId from old score bucket
        if (oldScore != 0) {
            bucketMap.get(oldScore).remove(contentId);
            if (bucketMap.get(oldScore).isEmpty()) {
                bucketMap.remove(oldScore);
                if (oldScore == maxPopularity) maxPopularity = newScore; // Adjust max score if needed
            }
        }

        // Update the node's popularity
        contentMap.put(contentId, new Node(contentId, newScore));

        // Add to new score bucket
        bucketMap.computeIfAbsent(newScore, k -> new LinkedHashSet<>()).add(contentId);
        maxPopularity = Math.max(maxPopularity, newScore);
    }

    public static void main(String[] args) {
        ContentPopularity_DLL tracker = new ContentPopularity_DLL();
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(1);
        tracker.decreasePopularity(2);
        System.out.println(tracker.mostPopular()); // Should print 1
    }
}