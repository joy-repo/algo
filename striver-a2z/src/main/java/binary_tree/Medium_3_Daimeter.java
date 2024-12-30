package binary_tree;


import binary_tree.MyBinaryTree.Node;

// Given the root of the Binary Tree, return the length of its diameter. The Diameter of a Binary Tree is the longest distance between any two nodes of that tree. This path may or may not pass through the root.
public class Medium_3_Daimeter {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        Container container = new Container();
        daimeter(root, container);
        System.out.println(container.val);
    }

    private static int daimeter(Node root, Container res) {
        if(root==null) return 0;

        int right = daimeter(root.right, res);
        int left = daimeter(root.left, res);

        int temp = Math.max(right+left+1, res.val);
        res.val=temp;

        return Math.max(right,left)+1;


    }

    static class Container{
        public int val=0;
    }
}
