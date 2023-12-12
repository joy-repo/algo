package com.tree;

public class Root2LeafSum {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        String str = rootToLeaf(root, 31);
        System.out.println(str);

        System.out.println("##### PRINT ALL LEAVES ######");

        allRootToLeaves(root, 31, "");

    }

    private static void allRootToLeaves(Node root, int res, String path) {

        if (root == null || res < 0) return;
        if (res == root.data && root.right==null && root.left==null) {
            System.out.println(path+","+root.data);
        }

        allRootToLeaves(root.left, res - root.data, path+","+root.data);
        allRootToLeaves(root.right, res - root.data, path+","+root.data);

    }

    private static String rootToLeaf(Node root, int res) {


        if (root == null || res < 0) return "";

        if (res == root.data && root.right==null && root.left==null) return root.data + "";


        String str = rootToLeaf(root.left, res - root.data);
        if (str.length() > 0) return str + "," + root.data;
        str = rootToLeaf(root.right, res - root.data);
        if (str.length() > 0) return str + "," + root.data;
        return "";
    }
}
