package binary_tree;

import binary_tree.MyBinaryTree.Node;

import java.util.Stack;

public class Medium_6_ZigzagTraversal {

    public static void main(String[] args) {
        Node root = MyBinaryTree.generateBinaryTree();
        zigzagTraversal(root);
    }

    private static void zigzagTraversal(Node root) {

        Stack<Node> stk1 = new Stack<>();
        Stack<Node> stk2 = new Stack<>();
        stk1.push(root);

        while(!stk1.isEmpty() && !stk1.isEmpty()){

            while(!stk1.isEmpty()){
                Node n = stk1.pop();
                System.out.println(n);
                if(n.left!=null) stk2.push(n.left);
                if(n.right!=null) stk2.push(n.right);
            }

            while(!stk2.isEmpty()){
                Node n = stk2.pop();
                System.out.println(n);
                if(n.right!=null) stk1.push(n.right);
                if(n.left!=null) stk1.push(n.left);
            }
        }

    }
}
