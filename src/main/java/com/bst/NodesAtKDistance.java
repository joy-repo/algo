package com.bst;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class NodesAtKDistance {

    static Node ROOT = BinaryTree.generateBinaryTree();
    static int target = 14;
    static int K = 3;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        dfs(ROOT);
        System.out.println(ans);
    }

    public static int dfs(Node node) {
        if (node == null)
            return -1;
        else if (node.data == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = dfs(node.left), R = dfs(node.right);
            if (L != -1) {
                if (L == K) ans.add(node.data);
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K) ans.add(node.data);
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public static  void subtree_add(Node node, int dist) {
        if (node == null|| dist>K) return;
        if (dist == K)
            ans.add(node.data);
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }

    }
}
