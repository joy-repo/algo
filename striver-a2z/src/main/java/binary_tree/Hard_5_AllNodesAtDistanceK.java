package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Hard_5_AllNodesAtDistanceK {

    static int NodeData = 5;
    static int distance = 3;

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();

        Map<Node, Node> child2parentMap = new HashMap<>();

        doChild2parentMap(root, child2parentMap);
        //System.out.print(child2parentMap);

        Node node = getTheNode(root, NodeData);

        printAllNodesAtDistanceK(node, distance, child2parentMap);
    }

    private static void printAllNodesAtDistanceK(Node node, int distance, Map<Node, Node> child2parentMap) {

        //BFS

        Stack<Pair> stk = new Stack<>();
        stk.push(new Pair(node, 0));
        Set<Node> alreadyAdded = new HashSet<>();
        alreadyAdded.add(node);

        while (!stk.isEmpty() ){
            Pair p = stk.pop();
            if(p.distance>distance) continue;

            if(p.distance==distance){
                System.out.print(p.n + ",");
                continue;
            }

            if(p.n.right!=null && !alreadyAdded.contains(p.n.right)){
                stk.push(new Pair(p.n.right, p.distance+1));
                alreadyAdded.add(p.n.right);
            }

            if(p.n.left!=null && !alreadyAdded.contains(p.n.left)){
                stk.push(new Pair(p.n.left, p.distance+1));
                alreadyAdded.add(p.n.left);
            }

            if(child2parentMap.containsKey(p.n) && !alreadyAdded.contains(child2parentMap.get(p.n))){
                stk.push(new Pair(child2parentMap.get(p.n), p.distance+1));
                alreadyAdded.add(child2parentMap.get(p.n));
            }
        }
    }

    private static Node getTheNode(Node root, int nodeData) {

        if(root==null) return null;
        if(root.data==nodeData) return root;

        Node NR = getTheNode(root.right, nodeData);
        if(NR!=null) return NR;
        Node NL = getTheNode(root.left, nodeData);
        if(NL!=null) return NL;
        return null;

    }

    private static void doChild2parentMap(Node root, Map<Node, Node> child2parentMap) {
        if(root==null) return;
        if(root.left==null && root.right==null) return;

        if(root.left!=null) {
            child2parentMap.put(root.left, root);
        }

        if(root.right!=null) {
            child2parentMap.put(root.right, root);
        }
        doChild2parentMap(root.left, child2parentMap);
        doChild2parentMap(root.right, child2parentMap);

    }

    static class Pair{
        public Node n;
        public int distance;

        public Pair(Node n, int distance){
            this.n=n;
            this.distance=distance;
        }
    }



//    private static void printAllNodesAtDistanceK_old(Node root, int nodeData, int distance) {
//
//        Node node = getTheNode(root, nodeData);
//        if(node==null){
//            System.out.print("No node with data : "+ nodeData);
//            return;
//        }
//
//
//    }



//    private static void printBelow(Node node, int distance) {
//        if(node==null) return;
//
//        if(distance==0) {
//            System.out.print(node+", ");
//            return;
//        }
//        printBelow(node.left, distance-1);
//        printBelow(node.right, distance-1);
//    }


}
