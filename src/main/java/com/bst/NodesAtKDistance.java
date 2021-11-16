package com.bst;

import java.util.ArrayList;
import java.util.List;

public class NodesAtKDistance {

    static Node ROOT = BinaryTree.generateBinaryTree();
    static int target = 14;
    static int K = 3;
    static List<Integer> ans = new ArrayList<>();

    public static void main(String[] args) {
        sol(ROOT);
        System.out.println(ans);
        ans = new ArrayList<>();
        //sol_Practice1(ROOT);
        System.out.println(ans);
    }

//    private static int sol_Practice1(Node root) {
//        if(root==null) return -1;
//
//        if(root.data==target) {
//            subtree_add1(root, 0);
//            return 1;
//        }
//
//        int L= sol_Practice1(root.left);
//        int R = sol_Practice1(root.right);
//
//
//
//
//
//    }

    private static void subtree_add1(Node root, int dis) {
        if (dis < 0) return;
        if (dis == 0) ans.add(root.data);
        subtree_add1(root.left, dis + 1);
        subtree_add1(root.right, dis + 1);
    }

    public static int sol(Node node) {
        if (node == null)
            return -1;
        else if (node.data == target) {
            subtree_add(node, 0);
            return 1;
        } else {
            int L = sol(node.left), R = sol(node.right);
            if (L != -1) {
                if (L == K) {
                    ans.add(node.data);
                }
                subtree_add(node.right, L + 1);
                return L + 1;
            } else if (R != -1) {
                if (R == K){ ans.add(node.data);}
                subtree_add(node.left, R + 1);
                return R + 1;
            } else {
                return -1;
            }
        }
    }

    // Add all nodes 'K - dist' from the node to answer.
    public static  void subtree_add(Node node, int dist) {

        if (dist == K)
            ans.add(node.data);
        if (node == null|| dist>K) return;
        else {
            subtree_add(node.left, dist + 1);
            subtree_add(node.right, dist + 1);
        }

    }
}
