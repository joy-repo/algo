package atlassian.practice;

import java.util.*;

public class FileReportGenerator {
    private final Map<String, Integer> collectionSizeMap = new HashMap<>();
    private int totalSize = 0;

    public void addFile(String fileName, int fileSize, String... collections) {
        totalSize += fileSize;
        for (String collection : collections) {
            collectionSizeMap.merge(collection, fileSize, Integer::sum);
        }
    }

    public int getTotalSize() {
        return totalSize;
    }

    public List<String> getTopNCollections(int N) {
        if (N <= 0) return Collections.emptyList();

        // Min-Heap to store the top N collections
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<String, Integer> entry : collectionSizeMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > N) {
                minHeap.poll(); // Remove smallest element if heap exceeds size N
            }
        }

        // Extract top N collections in descending order
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }
        Collections.reverse(result); // Reverse to get highest collections first
        return result;
    }

    public static void main(String[] args) {
        FileReportGenerator reportGenerator = new FileReportGenerator();

        // Adding files
        reportGenerator.addFile("file1.txt", 100);
        reportGenerator.addFile("file2.txt", 200, "collection1");
        reportGenerator.addFile("file3.txt", 200, "collection1");
        reportGenerator.addFile("file4.txt", 300, "collection2");
        reportGenerator.addFile("file5.txt", 10);

        // Fetching total size
        System.out.println("Total Size: " + reportGenerator.getTotalSize()); // Output: 810

        // Fetching top 2 collections
        System.out.println("Top 2 Collections: " + reportGenerator.getTopNCollections(2)); // Output: ["collection1", "collection2"]
    }
}