package com.bst;

public class MaxWidth {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
//        int res = maxWidth(root);
//        System.out.println(res);
//        System.out.println(maxLeft(root));
        System.out.println(maxLeft(root));
        System.out.println(maxRight(root));
        System.out.println(maxWidth(root));
    }

    private static int maxLeft(Node root) {

        if(root==null) return 0;
        return Math.max(maxLeft(root.left)+1, maxLeft(root.right)-1);
    }

    private static int maxRight(Node root) {

        if(root==null) return 0;
        return Math.max(maxLeft(root.left)-1, maxLeft(root.right)+1);
    }

//    private static int maxLeft(Node root) {
//        if(root==null) return 0;
//        return(Math.max(maxLeft(root.left)+1, maxLeft(root.right)-1));
//   }
//    private static int maxRight(Node root) {
//        if(root==null) return 0;
//        return(Math.max(maxLeft(root.left)-1, maxLeft(root.right)+1));
//    }
//
//    private static int maxWidth1(Node root) {
//        if(root==null) return 0;
//
//        int ll= maxWidth1(root.left)-1;
//        int rr= maxWidth1(root.right)+1;
//
//        return (rr+ll);
//
//
//    }

    private static int maxWidth(Node root) {
          if(root==null) return 0;

        int ll= Math.max(maxWidth(root.left)+1, maxWidth(root.right)-1);
        int rr= Math.max(maxWidth(root.left)-1, maxWidth(root.right)+1);

        return (rr+ll-1);


    }
}
