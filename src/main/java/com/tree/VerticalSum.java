package com.tree;

import java.util.HashMap;
import java.util.Map;

public class VerticalSum {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);

        root.left.left = new Node(4);
        root.left.right = new Node(5);

        root.right.left = new Node(7);
        root.right.right = new Node(6);

        //Node root = BinaryTree.generateBinaryTree();



        Map<Integer,Integer> res = new HashMap<>();
        verticalSumRecurr(root, res, 0);
        System.out.println(res);
    }

    private static void verticalSumRecurr(Node root, Map<Integer, Integer> res, int hLevel) {
        if(root==null) return ;

        res.putIfAbsent(hLevel,0);
        res.put(hLevel,res.get(hLevel)+root.data);
       // System.out.println(root.data);

        verticalSumRecurr(root.left, res, hLevel-1);

        verticalSumRecurr(root.right, res, hLevel+1);

    }
}
