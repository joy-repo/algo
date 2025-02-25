package atlassian;



import java.util.*;
        import java.util.concurrent.*;
        import java.util.concurrent.atomic.*;
        import java.util.concurrent.locks.*;

class ContentPopularityDLL_Thread {
    private static class Node {
        int contentId;
        int popularityScore;

        Node(int contentId, int popularity) {
            this.contentId = contentId;
            this.popularityScore = popularity;
        }
    }

    private final ConcurrentHashMap<Integer, Node> contentMap; // Maps contentId → Node
    private final ConcurrentHashMap<Integer, ConcurrentLinkedQueue<Integer>> bucketMap; // Maps popularity → Set of contentIds
    private final AtomicInteger maxPopularity; // Highest popularity
    private final StampedLock lock = new StampedLock(); // Reduces thread contention

    public ContentPopularityDLL_Thread() {
        contentMap = new ConcurrentHashMap<>();
        bucketMap = new ConcurrentHashMap<>();
        maxPopularity = new AtomicInteger(0);
    }

    public void increasePopularity(int contentId) {
        updatePopularity(contentId, 1);
    }

    public void decreasePopularity(int contentId) {
        updatePopularity(contentId, -1);
    }

    public int mostPopular() {
        long stamp = lock.tryOptimisticRead(); // Optimistic read (no blocking)
        int maxPop = maxPopularity.get();
        ConcurrentLinkedQueue<Integer> popularContents = bucketMap.get(maxPop);

        if (lock.validate(stamp)) { // Check if read was valid (no changes happened)
            return (popularContents == null || popularContents.isEmpty()) ? -1 : popularContents.peek();
        }

        // If optimistic read failed, acquire full read lock
        stamp = lock.readLock();
        try {
            maxPop = maxPopularity.get();
            popularContents = bucketMap.get(maxPop);
            return (popularContents == null || popularContents.isEmpty()) ? -1 : popularContents.peek();
        } finally {
            lock.unlockRead(stamp);
        }
    }

    private void updatePopularity(int contentId, int delta) {
        long stamp = lock.writeLock();
        try {
            Node node = contentMap.computeIfAbsent(contentId, id -> new Node(id, 0));
            int oldScore = node.popularityScore;
            int newScore = oldScore + delta;
            node.popularityScore = newScore;

            // Remove from old bucket
            if (oldScore != 0) {
                bucketMap.get(oldScore).remove(contentId);
                if (bucketMap.get(oldScore).isEmpty()) {
                    bucketMap.remove(oldScore);
                }
            }

            // Add to new bucket
            bucketMap.computeIfAbsent(newScore, k -> new ConcurrentLinkedQueue<>()).offer(contentId);

            // Update maxPopularity atomically
            maxPopularity.updateAndGet(currentMax -> Math.max(currentMax, newScore));
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public static void main(String[] args) {
        ContentPopularityDLL_Thread tracker = new ContentPopularityDLL_Thread();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> tracker.increasePopularity(1));
        executor.execute(() -> tracker.increasePopularity(2));
        executor.execute(() -> tracker.increasePopularity(1));
        executor.execute(() -> tracker.decreasePopularity(2));

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(tracker.mostPopular()); // Should print 1
    }
}