package com.striver79.linkedlist;


import com.striver79.arrays_hashing.NextPermutation;

public class MiddleElement {


    public static void main(String[] args) {
        SLNode root = new SLNode(1);
        root.next = new SLNode(2);
        root.next.next = new SLNode(3);
        root.next.next.next = new SLNode(4);
        root.next.next.next.next = new SLNode(5);
        root.next.next.next.next.next = new SLNode(6);
        root.next.next.next.next.next.next = new SLNode(7);
        root.next.next.next.next.next.next.next = new SLNode(8);
        findMiddle(root);
    }

    private static void findMiddle(SLNode root) {

        SLNode slow = root;
        SLNode fast = root;
        //Node prev =root;

        while(fast.next!=null && fast.next.next!=null){
           // prev = slow;
            slow=slow.next;
            fast =fast.next.next;
        }

        if(fast.next==null) System.out.println(slow.data);
        else System.out.println("slow.data:" + slow.data + " prev : "+ slow.next.data);

    }


}


