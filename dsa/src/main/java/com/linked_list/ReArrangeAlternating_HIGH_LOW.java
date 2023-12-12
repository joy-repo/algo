package com.linked_list;

public class ReArrangeAlternating_HIGH_LOW {


    public static void main(String[] args) {

        SegregateEvenOddNodes.Node root = new SegregateEvenOddNodes.Node(7);
        root.next=new SegregateEvenOddNodes.Node(15);
        root.next.next=new SegregateEvenOddNodes.Node(80);
        root.next.next.next=new SegregateEvenOddNodes.Node(12);
        root.next.next.next.next=new SegregateEvenOddNodes.Node(10);
        root.next.next.next.next.next = new SegregateEvenOddNodes.Node(5);

        SegregateEvenOddNodes.Node t = root.next.next.next.next.next;
        t.next=new SegregateEvenOddNodes.Node(4);
        t.next.next=new SegregateEvenOddNodes.Node(1);
        t.next.next.next = new SegregateEvenOddNodes.Node(7);
        t.next.next.next.next = new SegregateEvenOddNodes.Node(6);


    }



    static class Node{
        int data;
        SegregateEvenOddNodes.Node next;

        public  Node(int data){
            this.data=data;
        }

        public String toString(){
            return data+"";
        }
    }

}
