package binary_tree;

import binary_tree.MyBinaryTree.Node;

public class Hard_2_LCA {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        Node res =getLCA(root, 3, 9);
        System.out.print(res);
    }

    private static Node getLCA(Node root, int data1, int data2) {

        if(root==null) return null;

        if(root.data==data1 || root.data==data2){
            return root;
        }
        Node nR = getLCA(root.right, data1, data2);

        Node nL = getLCA(root.left,data1, data2);

        if(nR == null && nL==null) return null;
        if(nR!=null && nL!=null) return root;
        if(nR==null) return nL;
        else return nR;

    }
}
