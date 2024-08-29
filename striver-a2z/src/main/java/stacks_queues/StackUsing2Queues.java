package stacks_queues;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsing2Queues {

    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();

    public static void main(String[] args) {
        push(1);
        push(2);
        System.out.println(pop());
        push(3);
        push(4);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }

    private static Integer pop() {

        if(q1.isEmpty() && q2.isEmpty()) return Integer.MIN_VALUE ;

        if(q1.isEmpty()) return q2.poll();
         return q1.poll();
    }

    private static void push(int i) {

        if(q1.isEmpty() && q2.isEmpty()) {
            q1.offer(i);
            return;
        }

        if(q1.isEmpty()) {
            q1.offer(i);
            while (!q2.isEmpty()){
                q1.offer(q2.poll());
            }
        } else {
            q2.offer(i);
            while (!q1.isEmpty()){
                q2.offer(q1.poll());
            }
        }

    }


}
