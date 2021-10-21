package com.bst;

public class Height {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        int res = height(root);
        System.out.println(res);
    }

    private static int height(Node root) {

        if(root==null) return 0;
        return Math.max(height(root.left), height(root.right))+1;

    }
}
