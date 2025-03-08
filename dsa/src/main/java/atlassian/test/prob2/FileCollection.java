package atlassian.test.prob2;

//  Collection Files ->
// --> filename, size, CollectionName
//

//file1.txt (size: 100)
//
//file2.txt (size: 200) in collection "collection1"
//
//file3.txt (size: 200) in collection "collection1",
//
//file4.txt (size: 300) in collection "collection2"
//
//file5.txt (size: 10)

// -> total Size
// Top N collection by Size

///

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class FileCollection {

    @AllArgsConstructor
    static class FileEntry{
        String fileName;
        int fileSize;
        List<String> collection;
    }
// n entrties  c colle
//Space -> n + n(map) + n(queue) +k
// Time ->   c + logn + logk

    public  List<String> listOfTopNCollections(int n, List<FileEntry> list) {

        PriorityQueue<Map.Entry<String,Integer>> priorityQueue = new PriorityQueue<>((c1,c2)-> c2.getValue()-c1.getValue());
         Map<String, Integer> map = new HashMap<>();

         list.stream().filter(e-> Objects.nonNull(e.collection))
                 .forEach(e-> {
                     for(String col : e.collection) {
                         map.putIfAbsent(col, 0);
                         map.put(col, map.get(col) + e.fileSize);
                     }

                 });

         for(Map.Entry<String , Integer> entry : map.entrySet()){
             priorityQueue.offer(entry);
         }
         List<String> resList = new ArrayList<>();
         n = Math.min(n, priorityQueue.size());
         for(int i =1; i<=n; i++){
             String res = priorityQueue.poll().getKey();
             resList.add(res);
             System.out.println(res);
         }
        return resList;

    }

    //Space -> O(n)
    //Time-> O(n)
    public   int totalSizeAllFiles(List<FileEntry> list) {

       int res = list.stream().map(e->e.fileSize).mapToInt(e->e).sum();
       System.out.println(res);
       return  res;

    }


}
