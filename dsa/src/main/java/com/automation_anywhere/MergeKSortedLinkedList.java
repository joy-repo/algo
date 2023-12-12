package com.automation_anywhere;

public class MergeKSortedLinkedList {

    static class Node {
        int data;
        Node next;

        // Utility function to create a new node.
        Node(int key) {
            data = key;
            next = null;
        }
    }

    public static void main(String[] args) {
        Node[] arr = new Node[3];

        arr[0] = new Node(1);
        arr[0].next = new Node(3);
        arr[0].next.next = new Node(5);
        arr[0].next.next.next = new Node(7);

        arr[1] = new Node(2);
        arr[1].next = new Node(4);
        arr[1].next.next = new Node(6);
        arr[1].next.next.next = new Node(8);

        arr[2] = new Node(0);
        arr[2].next = new Node(9);
        arr[2].next.next = new Node(10);
        arr[2].next.next.next = new Node(11);

        // Merge all lists
        Node head = mergeKLists(arr);
        printList(head);
    }

    private static Node mergeKLists(Node[] arr) {

        Node[] indices = new Node[arr.length];

        for(int i=0; i< arr.length; i++){
            indices[i]=arr[i];
        }
        Node head = new Node(-10000);
        Node temp = head;


        while(true){
            int min = Integer.MAX_VALUE;
            int val =-1;
            int rr=0;
            for(int i=0; i< arr.length; i++){
                if(indices[i]==null){rr++; continue;}
                if(min> indices[i].data){
                    min=indices[i].data;
                    val=i;
                }
            }

            if(rr==arr.length-1) break;
            temp.next= new Node(min);
            temp=temp.next;
            indices[val]=indices[val].next;
        }
        return head.next;

    }

    static void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");

            node = node.next;
        }
        System.out.println();
    }
}
