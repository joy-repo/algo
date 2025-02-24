package atlassian;


/*
	1.	Basic Analysis:
	•	Input: A list where each entry contains:
	•	File Name: The name of the file.
	•	File Size: The size of the file.
	•	Collections: A list of collections to which the file belongs.
	•	Tasks:
	•	Total System Size: Calculate the cumulative size of all files.
	•	Top N Collections: Identify the top N collections based on the total size of files they encompass.
	2.	Advanced Analysis with Nested Collections:
	•	Input Enhancement: Collections can now contain other collections, forming a nested or hierarchical structure.
	•	Additional Task: Determine the top N collections by size, accounting for the sizes of files in all their nested sub-collections.
 */

/*




Imagine we have a system that stores files, and these files can be grouped into collections. We are interested in knowing where our resources are being taken up.
For this system we would like to generate a report that lists:
- The total size of all files stored;
- The top N collections (by file size) where N can be a user-defined value

Given a list of [FileName, FileSize, [Collection]] - Collection is optional,
i.e., a collection can have 1 or more files. Same file can be a part of more than 1 collection.


An example input into your report generator might look like:
file1.txt (size: 100)
file2.txt (size: 200) in collection "collection1"
file3.txt (size: 200) in collection "collection1"
file4.txt (size: 300) in collection "collection2"
file5.txt (size: 10)

So, basically collections are like tags and files can be tagged.
We have to implement methods that can return total size of all the files added to the system and topN collections.
TopN collections is based on total size of all the files tagged with particular collection. In above example "collection1" has 2 files with total size 400, "collection2" has 1 file with total size 300 and there are 2 files without any collection with total size 110.
Top2 collections would return ["collection1", "collection2"]
 */

import java.util.*;

class FileEntry {
    String fileName;
    int fileSize;
    List<String> collections;

    public FileEntry(String fileName, int fileSize, List<String> collections) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.collections = collections;
    }
}

class CollectionSizeCalculator {
    Map<String, Integer> collectionSizeMap = new HashMap<>();
    Map<String, List<String>> collectionHierarchy = new HashMap<>();
    Set<String> allCollections = new HashSet<>();

    // Process file data
    public void processFiles(List<FileEntry> files) {
        int totalSize = 0;

        for (FileEntry file : files) {
            totalSize = file.fileSize + totalSize;
            for (String collection : file.collections) {
                collectionSizeMap.put(collection, collectionSizeMap.getOrDefault(collection, 0) + file.fileSize);
                allCollections.add(collection);
            }
        }

        System.out.println("Total System Size: " + totalSize);
    }

    // Process collection relationships (for nested collections)
    public void processNestedCollections(List<String[]> parentChildRelations) {
        for (String[] relation : parentChildRelations) {
            String parent = relation[0], child = relation[1];
            collectionHierarchy.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            allCollections.add(parent);
            allCollections.add(child);
        }
    }

    // Recursively calculate total size for nested collections
    private int getNestedCollectionSize(String collection, Map<String, Integer> computedSizes) {
        if (computedSizes.containsKey(collection)) return computedSizes.get(collection);

        int totalSize = collectionSizeMap.getOrDefault(collection, 0);
        if (collectionHierarchy.containsKey(collection)) {
            for (String subCollection : collectionHierarchy.get(collection)) {
                totalSize += getNestedCollectionSize(subCollection, computedSizes);
            }
        }

        computedSizes.put(collection, totalSize);
        return totalSize;
    }

    // Get top N collections by size
    public void getTopNCollections(int N) {
        Map<String, Integer> computedSizes = new HashMap<>();
        for (String collection : allCollections) {
            getNestedCollectionSize(collection, computedSizes);
        }

        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>(
                (a, b) -> b.getValue() - a.getValue()
        );
        maxHeap.addAll(computedSizes.entrySet());

        System.out.println("Top " + N + " Collections by Size:");
        for (int i = 0; i < N && !maxHeap.isEmpty(); i++) {
            Map.Entry<String, Integer> entry = maxHeap.poll();
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Example Usage
public class CollectionStorageSystem {
    public static void main(String[] args) {
        List<FileEntry> files = Arrays.asList(
                new FileEntry("file1.txt", 100, Arrays.asList("A", "B")),
                new FileEntry("file2.txt", 200, Arrays.asList("A")),
                new FileEntry("file3.txt", 300, Arrays.asList("B", "C")),
                new FileEntry("file4.txt", 400, Arrays.asList("C"))
        );

        List<String[]> nestedCollections = Arrays.asList(
                new String[]{"X", "A"},
                new String[]{"X", "B"},
                new String[]{"B", "Y"},
                new String[]{"Y", "C"}
        );

        CollectionSizeCalculator calculator = new CollectionSizeCalculator();
        calculator.processFiles(files);
        calculator.processNestedCollections(nestedCollections);
        calculator.getTopNCollections(3);
    }
}