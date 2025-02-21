package atlassian;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKElements_CountSort {

    static int[] ARR = {1,1,7,2,2,3,2,7,9,7,7,7,2,8,9};
    static int K = 2;

    public static void main(String[] args) {
        topkCSort();
    }


    @AllArgsConstructor
    @ToString
    static class Bucket{
        public List<Integer> data;
        public int freq;
    }

    private static void topkCSort() {


        Map<Integer,Integer> frqMap= new HashMap<>();

        for(int r : ARR) {
            frqMap.put(r, frqMap.getOrDefault(r, 0) + 1);
        }
        Bucket[] freqArr = new Bucket[ARR.length];

        for(Map.Entry<Integer, Integer> e : frqMap.entrySet()){
            if(freqArr[e.getValue()]==null){
                List<Integer> list = new ArrayList<>();
                list.add(e.getKey());
                freqArr[e.getValue()]=new Bucket(list,1);
            } else {
                Bucket b = freqArr[e.getValue()];
                b.data.add(e.getKey());
            }
        }
        int k = K;
        List<Integer> res = new ArrayList<>();
        for(int i = freqArr.length-1; i>=0; i--){
            if(k==0) break;
            if(freqArr[i]!=null){
                res.addAll(freqArr[i].data);
                k=k-freqArr[i].data.size();
            }
        }
        System.out.println(res);
        System.out.println(frqMap);
        System.out.println(Arrays.asList(freqArr));
    }
}
