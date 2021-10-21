package com.bst;


class Node {
    public Node right;
    public Node left;
    public int data;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" + data + "}";
    }
}

public class BinaryTree {


    //  public Node root;

//                  10
//            8            5
//         6    9      2       18
//      3    7           14  17
//                              21

    public static Node generateBinaryTree() {

        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(5);
        root.left.left = new Node(6);
        root.left.right = new Node(9);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(7);

        root.right.left = new Node(2);
        root.right.left.right = new Node(14);
        root.right.right = new Node(18);
        root.right.right.left = new Node(17);
        root.right.right.left.right = new Node(21);

        return root;


    }


}
