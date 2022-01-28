package com;

public class MyBST {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insert(5);
        bt.insert(6);
        bt.insert(7);
        System.out.println("");
    }
}



class Node {

    Node right;
    Node left;
    Integer data;

    public Node(Integer i){
        data=i;
    }


}

class BinaryTree{

    Node root;


    public void insert(Integer i){
        if(root==null){
            root= new Node(i);
            return;
        }

        Node temp=root;
        Node pre =root;

        while(temp!=null){

            if(i<temp.data){
                pre=temp;
                temp=temp.left;

            } else {
                pre=temp;
                temp=temp.right;

            }
        }
        if(i<pre.data) {
            pre.left=new Node(i);
        } else {
            pre.right=new Node(i);
        }

    }

}