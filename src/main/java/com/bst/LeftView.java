package com.bst;

import java.util.LinkedList;
import java.util.Queue;

public class LeftView {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        giveLeftView(root);
    }

    private static void giveLeftView(Node root) {

        if(root==null) return;
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(root);
        while(!queue1.isEmpty() || !queue2.isEmpty()) {
            boolean isFirst=false;
            while (!queue1.isEmpty()) {
                Node n = queue1.poll();
                if(isFirst==false) {
                    System.out.print(n.data + ",");
                    isFirst=true;
                }
                if (n.left != null) queue2.add(n.left);
                if (n.right != null) queue2.add(n.right);
            }
            isFirst=false;
            while (!queue2.isEmpty()) {
                Node n = queue2.poll();
                if(isFirst==false) {
                    System.out.print(n.data + ",");
                    isFirst=true;
                }
                if (n.left != null) queue1.add(n.left);
                if (n.right != null) queue1.add(n.right);
            }
        }
    }
}
