package stacks_queues.monotonic;

//https://takeuforward.org/data-structure/next-greater-element-using-stack/

import java.util.Stack;

public class NGE {

    static int[] ARR = {5,7,1,2,6,8};

    public static void main(String[] args) {
        solu();
    }

    private static void solu() {

        int[] res = new int[ARR.length];
        Stack<Integer> stk = new Stack<>();

        for(int i = ARR.length-1; i>=0; i--){

                while (!stk.isEmpty() && stk.peek()<= ARR[i]){
                    stk.pop();
                }
                if(stk.isEmpty()) res[i] = -1;
                else res[i] = stk.peek();
                stk.push(ARR[i]);

            }

        System.out.println(res);
    }
}
