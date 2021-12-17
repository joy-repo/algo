package com.linked_list;

public class SegregateEvenOddNodes {

    public static void main(String[] args) {
        Node root = new Node(7);
        root.next=new Node(15);
        root.next.next=new Node(80);
        root.next.next.next=new Node(12);
        root.next.next.next.next=new Node(10);
        root.next.next.next.next.next = new Node(5);

        Node t = root.next.next.next.next.next;
        t.next=new Node(4);
        t.next.next=new Node(1);
        t.next.next.next = new Node(7);
       t.next.next.next.next = new Node(6);

        root= sol(root);

        System.out.println(root);

    }

    private static Node sol(Node root) {

        Node evenNodeEnd=null;
        Node oddNodeStart=null;
        Node temp = root;
        Node temp_previous =null;
        Node newRoot=null;

        while(temp!=null){

            if(temp.data%2==0){

                if(evenNodeEnd==null && oddNodeStart==null){
                    evenNodeEnd=temp;
                    newRoot=evenNodeEnd;
                    temp_previous=temp;
                    temp=temp.next;
                    continue;
                }
                if(oddNodeStart==null ){
                    temp_previous=temp;
                    temp=temp.next;
                    continue;
                }
                if(newRoot==null){
                    evenNodeEnd=temp;
                    temp_previous.next=temp.next;
                    temp=temp_previous.next;
                    newRoot=evenNodeEnd;
                    evenNodeEnd.next=oddNodeStart;
                    continue;
                }
                else {
                    evenNodeEnd.next = temp;
                    temp_previous.next = temp.next;
                    temp = temp_previous.next;
                    evenNodeEnd = evenNodeEnd.next;
                    evenNodeEnd.next = oddNodeStart;
                    continue;
                }


            }

            if(temp.data%2==1){
                if(oddNodeStart==null){
                    oddNodeStart=temp;
                }
            }
            temp_previous=temp;
            temp=temp.next;
        }
        System.out.println(newRoot);
        return newRoot!=null?newRoot:root;

    }


    static class Node{
        int data;
        Node next;

        public  Node(int data){
            this.data=data;
        }

        public String toString(){
            return data+"";
        }
    }

}


