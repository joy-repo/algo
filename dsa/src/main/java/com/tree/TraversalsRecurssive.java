package com.tree;

public class TraversalsRecurssive {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        System.out.println("###### INORDER -- (Left, Root, Right) ######### ");
        inOrder(root);
        System.out.println();
        System.out.println("###### PREORDER -- (Root, Left, Right) ######### ");
        preOder(root);
        System.out.println();
        System.out.println("###### PostORDER -- (Left, Right, Root) ######### ");
        postOder(root);


    }

    private static void postOder(Node root) {
        if (root == null) return;


        preOder(root.left);

        preOder(root.right);

        System.out.print(root.data + ",");
    }

    private static void preOder(Node root) {
        if (root == null) return;
        System.out.print(root.data + ",");

        preOder(root.left);

        preOder(root.right);
    }

    private static void inOrder(Node root) {

        if (root == null) return;

        inOrder(root.left);
        System.out.print(root.data + ",");
        inOrder(root.right);
    }
}
