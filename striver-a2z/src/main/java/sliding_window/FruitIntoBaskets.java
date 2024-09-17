package sliding_window;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FruitIntoBaskets {

    static int[] ARR = {1,2,3,2,2};
    static int BASKET=2;

    public static void main(String[] args) {
        solution();
    }

    private static void solution() {

        int fruits = 0;
        int res=0;
        int l =0;
        int r=0;
        int k =0;
        Map<Integer, Integer> map = new HashMap<>();

        while(r < ARR.length){

            map.putIfAbsent(ARR[r],0 );
            map.put(ARR[r], map.get(ARR[r])+1);


            if(map.size() <= BASKET){
                fruits++;
                res = Math.max(res, fruits);
                r++;
            } else{
                fruits++;
                while(map.size() > BASKET){
                    map.put(ARR[l], map.get(ARR[l])-1);
                    if(map.get(ARR[l])==0){
                        map.remove(ARR[l]);
                    }
                    l++;
                    fruits--;
                }
                r++;
            }
        }
        res = Math.max(res, fruits);
        System.out.println(res);
    }
}
