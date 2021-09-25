package com.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class TopView {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        Map<Integer, Integer> map = new HashMap<>();
        topViewRecurr(root,map, 0);
        System.out.println(map);

        topView(root);

    }

    private static void topView(Node root) {

        Queue<Node> q1 = new LinkedList<>();

    }

    private static void topViewRecurr(Node root, Map<Integer, Integer> map, int level) {
        if(root==null) return;

        map.putIfAbsent(level, root.data);
        System.out.println("level:" + level + "--"+ root.data);
        topViewRecurr(root.left,map,level-1);
        topViewRecurr(root.right,map,level+1);
    }
}
