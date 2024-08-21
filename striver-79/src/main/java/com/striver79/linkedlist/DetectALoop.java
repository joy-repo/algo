package com.striver79.linkedlist;

public class DetectALoop {

    public static void main(String[] args) {

        SLNode root = new SLNode(1);
        root.next = new SLNode(2);
        root.next.next = new SLNode(3);
        root.next.next.next = new SLNode(4);
        root.next.next.next.next = new SLNode(5);
        root.next.next.next.next.next = new SLNode(6);
        root.next.next.next.next.next.next = new SLNode(7);
        root.next.next.next.next.next.next.next = new SLNode(8);
        root.next.next.next.next.next.next.next.next = root.next.next.next.next;
        System.out.println(findCycle(root));

    }

    private static boolean findCycle(SLNode root) {


        SLNode slow = root;
        SLNode fast = root;
        do{
            slow=slow.next;
            fast=fast.next.next;
        }
        while(fast!=null && slow.data != fast.data);
        if(fast==null) return false;
        return true;
    }
}


