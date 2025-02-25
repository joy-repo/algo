package atlassian;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKElements_Heap {

    static int[] ARR = {1,1,2,2,3,9,7,7,7,8,9};
    static int K = 4;

    public static void main(String[] args) {
        topK();
    }

    private static void topK() {

        Map<Integer,Integer> frqMap= new HashMap<>();

        for(int r : ARR) {
            frqMap.put(r, frqMap.getOrDefault(r, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> prq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for(Map.Entry<Integer,Integer> e : frqMap.entrySet()){
            prq.offer(e);
            while(prq.size()>K){
                prq.poll();
            }
        }
        System.out.println(prq);

    }
}
