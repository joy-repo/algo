package binary_tree;

import binary_tree.MyBinaryTree.Node;

public class Medium_2_BalancedBT {

    public static void main(String[] args) {

        Node node = MyBinaryTree.generateBinaryTree();
        if(checkIfBalanced(node)<0) System.out.println("false");
        else System.out.println("true");
    }

    private static int checkIfBalanced(Node node) {

        if(node==null) return 0;

        int right = checkIfBalanced(node.right);
        if(right==-1) return -1;
        int left = checkIfBalanced(node.left);
        if(left==-1) return -1;
        if(Math.abs(right-left)>1) return -1;
        return Math.max(right,left)+1;


    }
}
