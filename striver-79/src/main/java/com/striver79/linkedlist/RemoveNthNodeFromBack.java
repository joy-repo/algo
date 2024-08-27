package com.striver79.linkedlist;

public class RemoveNthNodeFromBack {

    public static void main(String[] args) {
        SLNode root = new SLNode(1);
        root.next = new SLNode(2);
        root.next.next = new SLNode(3);
        root.next.next.next = new SLNode(4);
        root.next.next.next.next = new SLNode(5);
        root.next.next.next.next.next = new SLNode(6);
        root.next.next.next.next.next.next = new SLNode(7);
        root.next.next.next.next.next.next.next = new SLNode(8);
        remove(root, 3);
    }

    private static void remove(SLNode root, int nth) {

        SLNode node = root;
        for(int i=0; i<nth; i++){
            node = node.next;
        }
        SLNode res = root;

        while (node!=null){
            node = node.next;
            res = res.next;
        }
        System.out.println(res.data);

    }


}
