package com.bst.techiedelight;

import com.bst.BinaryTree;
import com.bst.Node;

import java.util.LinkedList;
import java.util.Queue;

public class Height {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        int s = solbyRecc(root);
        System.out.println(s);
        solByItr(root);
    }

    private static void solByItr(Node root) {

        Queue<Node> queue = new LinkedList<>();
        int hgt = 0;
        int levelSize = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int temp = 0;
            hgt++;
            while (levelSize > 0) {
                Node n = queue.poll();
                if (n.right != null) {
                    temp++;
                    queue.offer(n.right);
                }
                if (n.left != null) {
                    temp++;
                    queue.offer(n.left);
                }
                levelSize--;
            }
            levelSize = temp;
        }
        System.out.println(hgt);
    }

    private static int solbyRecc(Node root) {
        if (root == null) return 0;

        return Math.max(solbyRecc(root.left), solbyRecc(root.right)) + 1;

    }
}
