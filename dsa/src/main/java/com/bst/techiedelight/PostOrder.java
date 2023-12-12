package com.bst.techiedelight;

import com.bst.BinaryTree;
import com.bst.Node;

import java.util.Stack;

public class PostOrder {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        solByRecc(root);
        System.out.println();
        solByItr(root);
    }

    private static void solByItr(Node root) {

        Stack<Node> stk = new Stack<>();
        stk.push(root);
        Node n = root;
        Node prev=null;

        while (!stk.isEmpty()){

            while (n.left!=null){
                n=n.left;
                stk.push(n);
            }
            //Node prev=null;

//            if(stk.peek().right.data==n.data){
//                System.out.print(stk.pop().data+" ,");
//            }

            while(!stk.isEmpty()){
                n = stk.peek();
                if(n.right!=null && prev!=null && prev!=n.right){
                     n=n.right;
                     stk.push(n);
                     break;
                }else{
                    prev = stk.pop();
                    System.out.print(n.data+" ,");
                }
            }
        }
    }

    private static void solByRecc(Node root) {
        if(root==null) return;
        solByRecc(root.left);
        solByRecc(root.right);
        System.out.print(root.data+" ,");
    }
}
