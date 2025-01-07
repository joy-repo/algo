package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.ArrayList;
import java.util.List;



public class Medium_7_BoundaryTraversal_AntiClockWise {


    public static void main(String[] args) {
        Node node = MyBinaryTree.generateBinaryTree();
        List<Node> res = new ArrayList<>();
    //    System.out.println(node);

        //leftBoundary - top to bottom
        leftBoundary(node, res);

        // leaf Node from left to right
        leafNode(node, res);

        // rightBoundary bottom to top
        rightBoundary(node, res);
        System.out.println(res);
    }

    private static void rightBoundary(Node node, List<Node> res) {

        if(node==null || isLeafNode(node)) return;

        if(node.right!=null){
            rightBoundary(node.right, res);
            res.add(node);
            return;
        }
        if(node.left!=null){
            rightBoundary(node.left, res);
            res.add(node);
        }
    }

    private static void leafNode(Node node, List<Node> res) {

        if(node==null ) return;

        if(isLeafNode(node)) {res.add(node); return;}

        leafNode(node.left, res);
        leafNode(node.right, res);

    }

    private static void leftBoundary(Node node, List<Node> res) {

        if(node == null || isLeafNode(node)) return;
        res.add(node);
        if(node.left!=null){
            leftBoundary(node.left, res);
            return;
        }

        if(node.right!=null){
            leftBoundary(node.right, res);
        }
    }

    private static boolean isLeafNode(Node node) {
        if(node.left==null && node.right==null) return true;
        return false;
    }
}
