package com.bst;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LeftView {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        giveLeftView(root);
        System.out.println();
        giveLeftView_Practice1(root);
        System.out.println();
        Map<Integer, Integer> res = new LinkedHashMap<>();
        giveLeftView_RECC(root, res, 1);
        System.out.println(res);
    }

    //recurrsion
    private static void giveLeftView_RECC(Node root, Map<Integer, Integer> res, int level) {
        if (root == null) return;
        res.putIfAbsent(level, root.data);
        giveLeftView_RECC(root.left, res, level + 1);
        giveLeftView_RECC(root.right, res, level + 1);

    }

    //optimised
    private static void giveLeftView_Practice1(Node root) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 1; i <= len; i++) {
                Node node = queue.poll();
                if (i == 1)
                    System.out.print(node.data + ",");
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
    }


    private static void giveLeftView(Node root) {

        if (root == null) return;
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(root);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            boolean isFirst = false;
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
