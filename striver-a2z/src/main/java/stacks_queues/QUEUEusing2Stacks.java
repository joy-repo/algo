package stacks_queues;

import java.util.Stack;

public class QUEUEusing2Stacks {

    static Stack<Integer> s1= new Stack();
    static Stack<Integer> s2= new Stack();

    public static void main(String[] args) {

        offer(1);
        offer(2);
        System.out.println(take());
        offer(3);
        System.out.println(take());
        System.out.println(take());
        System.out.println(take());
        offer(4);
        System.out.println(take());
    }

    private static int take() {
        if(!s2.isEmpty()) return s2.pop();

        while (!s1.isEmpty()){
            s2.push(s1.pop());
        }
        if(!s2.isEmpty()) return s2.pop();
        return Integer.MIN_VALUE;
    }

    private static void offer(int i) {
        s1.push(i);
    }




}
