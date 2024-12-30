package binary_tree;

import binary_tree.MyBinaryTree.Node;


public class Medium_1_HeightBinaryTree {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        int height = getHeight(root);
        System.out.println(height);
    }

    private static int getHeight(Node root) {

        if (root==null) return 0;

        return Math.max(getHeight(root.left), getHeight(root.right))+1;

    }
}
