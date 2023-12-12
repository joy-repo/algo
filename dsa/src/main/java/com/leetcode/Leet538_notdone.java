package com.leetcode;

import com.bst.BinaryTree;
import com.bst.Node;

public class Leet538_notdone {

    static Node ROOT = BinaryTree.generateBinaryTree();
    static int SUM=0;

    public static void main(String[] args) {
        convertBST(ROOT);

    }

    private static void convertBST(Node root) {
        System.out.println(root);
        SUM = getSum(root);
        convertBSTUtil(root);
        System.out.println("------------");
        System.out.println(root);
    }

    private static void convertBSTUtil( Node root) {
        if (root != null) {
            convertBSTUtil(root.left);
            int temp=SUM- root.data;
            root.data = SUM;
            SUM=temp;
            convertBSTUtil(root.right);
        }

    }

    private static int getSum(Node root) {

        if (root == null) return 0;
        return (root.data + getSum(root.left) + getSum(root.right));
    }
}
