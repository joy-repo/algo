package com.bst;

public class LCA {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        //Node n = lca(root, 12, 60);
        //  System.out.println(n);

        int[] arr = {-1, -1};
//        Node n =lcaINP(root, 12, 18,false,false);
//        System.out.println(n);
//        System.out.println(Arrays.toString(arr));
        System.out.println(lca(root, 2, 21));


    }

    private static Node lca(Node n, int n1, int n2) {

        if (n == null) return null;

        if (n.data == n1 || n.data == n2) return n;

        Node right = lca(n.right, n1, n2);

        Node left = lca(n.left, n1, n2);

        if (right != null && left != null) return n;
        if (right == null && left == null) return null;


        return right == null ? left : right;

    }


//    private static Node lca_P(Node node,  int n1, int n2){
//        if(node==null) return null;
//
//        if(n1== node.data || n2== node.data) return node;
//
//        Node right = lca_P(node.right, n1,n2);
//        Node left = lca_P(node.left,n1,n2);
//
//        if(right!=null && left!=null ) return node;
//        if(right==null && left==null) return null;
//        return right!=null ? right:left;
//    }

    private static Node lcaINP(Node n, int n1, int n2, boolean gotN1, boolean gotN2) {

        if (n == null) return null;

        if (gotN1 && gotN2) return n;

        if (n1 == n.data) {
            gotN1 = true;
        }

        if (n2 == n.data) {
            gotN2 = true;
        }
       // if(gotN1 && gotN2) return n;
        Node right=lcaINP(n.right, n1, n2, gotN1, gotN2);

        Node left=lcaINP(n.left, n1, n2, gotN1,gotN2);

//        if (arr[0]==1 && arr[1]==1) return n;
//        if (arr[0]==-1 && arr[0]==-1) return null;


        return gotN1&&gotN2 ? n : null;

    }



}
