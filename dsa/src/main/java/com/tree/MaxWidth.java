package com.tree;

public class MaxWidth {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        int res = maxWidth(root);
    }

    private static int maxLeft(Node root) {
        if(root==null) return 0;
        return(Math.max(maxLeft(root.left)+1, maxLeft(root.right)-1));


    }


    private static int maxWidth(Node root) {
          if(root==null) return 0;

        int ll= maxWidth(root.left)-1;
        int rr= maxWidth(root.right)+1;

        return (rr-ll);


    }
}
