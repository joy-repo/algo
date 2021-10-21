package com.bst;

import java.util.List;

public class GetPAth {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
//        List<Integer> list = new ArrayList<>();
//         getPath(root, 17, list);
//         System.out.println(list);
        int y =getLevel(root,17);
        System.out.println(y);
    }

    private static int getLevel(Node root, int n){

        if(root==null){
            return Integer.MIN_VALUE;
        }
        if(root.data==n){
            return 0;
        }

        int r = getLevel(root.right, n);
        if(r!=Integer.MIN_VALUE) return r+1;
        int l = getLevel(root.left, n);


        if(l!=Integer.MIN_VALUE) return  l+1;
        return  Integer.MIN_VALUE;

    }

    private static boolean getPath(Node root, int n, List<Integer> list) {

        if(root==null) return false;
        if(root.data==n) {
            list.add(root.data);
            return true;
        }

        if(getPath(root.left, n , list)){
            list.add(root.data);
            return true;
        }

        if(getPath(root.right, n , list)){
            list.add(root.data);
            return true;
        }
        return false;
    }
}
