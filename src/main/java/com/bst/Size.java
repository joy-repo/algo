package com.bst;

public class Size {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        int res = size(root);
        System.out.println(res);
    }

    private static int size(Node root) {

        if(root==null) return 0;
        return size(root.left)+size(root.right)+1;

    }
}
