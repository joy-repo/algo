package stacks_queues.monotonic;

//TODO:// previous Greater Element

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PGEwithIndex {

    static  int[] ARR = {7,2,1,3,3,1,1,8};

    public static void main(String[] args) {
        pge();
    }

    private static void pge() {

        Stack<Integer> stk = new Stack<>();
        List<Integer> res = new ArrayList<>();

        for(int i =0; i< ARR.length; i++){

            while (!stk.isEmpty() && ARR[stk.peek()]< ARR[i]){
                stk.pop();
            }
            res.add(stk.isEmpty() ? -1: stk.peek());
            stk.push(i);
        }
       System.out.println(res);
        //res.stream().map(i->ARR[i]).forEach(System.out::println);

    }
}
