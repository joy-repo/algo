package com.bst.techiedelight;

import com.bst.BinaryTree;
import com.bst.Node;

import java.util.Stack;

public class Preorder {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        solBYTECC(root);
        System.out.println();
        solByITR(root);
    }

    private static void solByITR(Node root) {

        Stack<Node> stk = new Stack<>();
        stk.push(root);
        Node n = root;
        System.out.print(n.data+" ,");

        while(!stk.isEmpty()){

            while (n.left != null) {
                n = n.left;
                System.out.print(n.data+" ,");
                stk.push(n);
            }

            while (!stk.isEmpty()){
                n = stk.pop();
                if(n.right!=null){
                    System.out.print(n.right.data+" ,");
                    stk.push(n.right);
                    n =n.right;
                    break;
                }
            }
        }


    }

    private static void solBYTECC(Node root) {
        if(root==null) return;
        System.out.print(root.data+ " ,");
        solBYTECC(root.left);
        solBYTECC(root.right);
    }


}
