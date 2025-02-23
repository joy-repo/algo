package atlassian;

import lombok.AllArgsConstructor;

import java.util.*;

/*
Operation	Complexity
increasePopularity() / decreasePopularity()	O(log N) (heap insert)
mostPopular()	O(1) ~ O(log N) (heap peek & cleanup)

 */

class ContentPopularityPriorityQueue {

    @AllArgsConstructor
    class ContentContainer{
        public int contentId;
        public int popularityScore;

    }

    private Map<Integer, Integer> popularityMap; // Maps contentId -> popularity
    private PriorityQueue<ContentContainer> maxHeap; // Max heap for (popularity, contentId)

    public ContentPopularityPriorityQueue() {
        popularityMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> {
            if (a.popularityScore != b.popularityScore) return Integer.compare(b.popularityScore, a.popularityScore); // Higher popularity first
            return 1; // If tie, smaller contentId first
        });
    }

    public void increasePopularity(int contentId) {
        updatePopularity(contentId, 1);
    }

    public void decreasePopularity(int contentId) {
        updatePopularity(contentId, -1);
    }

    public int mostPopular() {
        if (!maxHeap.isEmpty()) {
            return maxHeap.poll().contentId;
        }
        return -1; // No content available
    }

    private void updatePopularity(int contentId, int delta) {
        int newScore = popularityMap.getOrDefault(contentId, 0) + delta;
        popularityMap.put(contentId, newScore);
        maxHeap.offer(new ContentContainer(contentId,newScore)); // Push updated score
    }

    public static void main(String[] args) {
        ContentPopularityPriorityQueue tracker = new ContentPopularityPriorityQueue();
        tracker.increasePopularity(1);
        tracker.increasePopularity(2);
        tracker.increasePopularity(1);
        tracker.decreasePopularity(2);
        System.out.println(tracker.mostPopular()); // Should print 1
    }
}