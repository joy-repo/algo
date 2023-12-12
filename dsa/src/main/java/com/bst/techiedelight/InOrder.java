package com.bst.techiedelight;

import com.bst.BinaryTree;
import com.bst.Node;

import java.util.Stack;

public class InOrder {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        solByRECC(root);
        SolByITR(root);
    }

    private static void SolByITR(Node root) {


        System.out.println();
        Stack<Node> stk = new Stack<>();
        stk.push(root);
        Node n = root;

        while (!stk.isEmpty()) {

            while (n.left != null) {
                n = n.left;
                stk.push(n);
            }

            while (!stk.isEmpty()) {
                n = stk.pop();
                System.out.print(n.data + " ,");
                if (n.right != null) {
                    stk.push(n.right);
                    n = n.right;
                    break;
                }
           }
        }
    }

    private static void solByRECC(Node root) {

        if (root == null) return;

        solByRECC(root.left);
        System.out.print(root.data + " ,");
        solByRECC(root.right);

    }


}
