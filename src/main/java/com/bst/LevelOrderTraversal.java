package com.bst;

import java.util.*;

public class LevelOrderTraversal {

    public static void main(String[] args) {
        Node root = BinaryTree.generateBinaryTree();
        //levelOrderREC(root);
        System.out.println("##### LEVEL ORDER ##### RECURSION");
        int i=0;
        while(true) {
            List<Integer> ll = new ArrayList<>();
            printNodeAtCurrentLevel(root, 0, i, ll);
            if(ll.isEmpty()) break;
            i++;
        }
        System.out.println("\n##### LEVEL ORDER ##### QUEUE");
        levelOrderQueue(root);

        System.out.println("\n##### ZIGZAG ORDER ##### QUEUE");
        zigZagOrderQueue(root);

        System.out.println("\n##### ZIGZAG ORDER ##### STACK");
        zigZagOrderStack(root);

    }

    private static void zigZagOrderStack(Node root) {

        if (root == null) return;
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack2.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                Node n = stack1.pop();
                System.out.print(n.data + ",");
                if (n.left != null) stack2.push(n.left);
                if (n.right != null) stack2.push(n.right);
            }
            while (!stack2.isEmpty()) {
                Node n = stack2.pop();
                System.out.print(n.data + ",");
                if (n.right != null) stack1.push(n.right);
                if (n.left != null) stack1.push(n.left);
            }
        }



    }

    private static void zigZagOrderQueue(Node root) {
        if(root==null) return;
        Queue<Node> queueR2L = new LinkedList<>();
        Queue<Node> queueL2R = new LinkedList<>();
        queueR2L.add(root);

        while(!queueR2L.isEmpty() || !queueL2R.isEmpty()){
            while(!queueR2L.isEmpty()){
                Node n = queueR2L.poll();
                System.out.print(n.data+",");
                if(n.left!=null) queueL2R.offer(n.left);
                if(n.right!=null) queueL2R.offer(n.right);
            }
            while(!queueL2R.isEmpty()){
                Node n = queueL2R.poll();
                System.out.print(n.data+",");
                if(n.right!=null) queueR2L.offer(n.right);
                if(n.left!=null) queueR2L.offer(n.left);
            }
        }
    }

    private static void levelOrderQueue(Node root) {
        if(root==null) return;
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()){
            Node n = queue.poll();
            System.out.print(n.data+",");
            if(n.left!=null) queue.add(n.left);
            if(n.right!=null) queue.add(n.right);
        }
    }


    private static void printNodeAtCurrentLevel(Node root, int i, int level, List<Integer> ll) {

        if(root==null) return ;

        if(i==level) {System.out.print(root.data+","); ll.add(root.data);}
        printNodeAtCurrentLevel(root.left, i+1, level, ll);
        printNodeAtCurrentLevel(root.right,i+1,level, ll);


    }


}
