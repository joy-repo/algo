package com.striver79.linkedlist;


import com.striver79.arrays_hashing.NextPermutation;

public class MiddleElement {


    public static void main(String[] args) {
        Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
//        root.next.next.next = new Node(4);
//        root.next.next.next.next = new Node(5);
//        root.next.next.next.next.next = new Node(6);
//        root.next.next.next.next.next.next = new Node(7);
//        root.next.next.next.next.next.next.next = new Node(8);
        findMiddle(root);
    }

    private static void findMiddle(Node root) {

        Node slow = root;
        Node fast = root;
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


class Node {
    public int data;
    public Node next;

    public Node(int data){
        this.data=data;
    }

}
