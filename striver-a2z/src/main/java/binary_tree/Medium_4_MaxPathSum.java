package binary_tree;

import binary_tree.MyBinaryTree.Node;

public class Medium_4_MaxPathSum {

    public static void main(String[] args) {
        Node node = MyBinaryTree.generateBinaryTree();
        int ans = maxPathSum(node);
        System.out.println(ans);
    }

    private static int maxPathSum(Node node) {

        if(node==null) return 0;

        int right = maxPathSum(node.right);
        int left = maxPathSum(node.left);

        return Math.max(right,left)+node.data;
    }
}
