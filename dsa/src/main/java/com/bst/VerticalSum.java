package com.bst;

import java.util.HashMap;
import java.util.Map;

public class VerticalSum {

    static Node ROOT=BinaryTree.generateBinaryTree();
    static Map<Integer,Integer> VERTICAL_SUM = new HashMap<>();

    public static void main(String[] args) {
        sol(ROOT,0);
        System.out.println(VERTICAL_SUM);

    }

    private static void sol(Node node, int vLevel) {

        if(node==null) return;
        VERTICAL_SUM.putIfAbsent(vLevel,0);
        VERTICAL_SUM.put(vLevel,VERTICAL_SUM.get(vLevel)+ node.data);


        sol(node.left,vLevel-1);
        sol(node.right,vLevel+1);

    }
}
