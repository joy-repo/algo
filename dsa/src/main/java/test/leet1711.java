package test;

import java.util.HashMap;
import java.util.Map;

public class leet1711 {

    public static int[] deliciousness = {1,1,1,3,3,3,7};

    public static void main(String[] args) {
        solleet();
    }

    private static void solleet() {

        int res = 0;
        int power = 2;
        for(int i =1; i<22; i++){
            Map<Integer, Integer> map = new HashMap<>();
            for(int num : deliciousness){
                res = res + map.getOrDefault(power-num, 0);
                map.merge(num, 1, Integer::sum);
            }
            power=power*2;
        }
        System.out.print(res);
    }
}
