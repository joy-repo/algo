package binary_tree;


import binary_tree.MyBinaryTree.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Medium_8_VerticalOrderTraversal {

    public static void main(String[] args) {
        Node node = MyBinaryTree.generateBinaryTree();

        Map<Integer, Map<Integer, List<Node>>> res = new HashMap<>();
        // horiIndex       vertIndex

        verticalTraverse(node,0, 0, res);

        TreeSet<Integer> sortedSet = new TreeSet<>(res.keySet());
        List<Node> ans = new ArrayList<>();

        for( Integer i : sortedSet){
            Map<Integer, List<Node>> listt = res.get(i);
            TreeSet<Integer> sortedSetTemp = new TreeSet<>(listt.keySet());
            for (Integer j : sortedSetTemp){
                List<Node> nodeList = listt.get(j);
                Collections.sort(nodeList, Comparator.comparingInt(n -> n.data));
                ans.addAll(nodeList);
            }
        }
        System.out.print(ans);
    }

    private static void verticalTraverse(Node node,int horiIndex, int vertIndex, Map<Integer, Map<Integer, List<Node>>> res) {

        if(node==null) return;

        res.putIfAbsent(horiIndex, new HashMap<>());
        res.get(horiIndex).putIfAbsent(vertIndex, new ArrayList<>());
        res.get(horiIndex).get(vertIndex).add(node);

        verticalTraverse(node.left, horiIndex-1, vertIndex+1, res);
        verticalTraverse(node.right, horiIndex+1, vertIndex+1, res);

    }
}
