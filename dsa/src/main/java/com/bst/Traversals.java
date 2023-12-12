package com.bst;

import java.util.Stack;

public class Traversals {

	public static void main(String[] args) {

		BinaryTree1 tree = new BinaryTree1();

		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		
		System.out.println("PREORDER");
		
		preOrder_itr(tree.root);
		System.out.println("INORDER");
		inOrder_itr(tree.root);
		System.out.println("POSTORDER");
		postOrder_itr(tree.root);
		

	}

	public void inOrder_recc(Node n) {

		if (n == null)
			return;

		inOrder_recc(n.left);
		System.out.println(n.data);
		inOrder_recc(n.right);
	}

	public void preOrder_recc(Node n) {

		if (n == null)
			return;
		System.out.println(n.data);
		preOrder_recc(n.left);
		preOrder_recc(n.right);
	}

	public static void postOrder_recc(Node n) {

		if (n == null)
			return;

		postOrder_recc(n.left);
		postOrder_recc(n.right);
		System.out.println(n.data);
	}

	public static void preOrder_itr(Node n) {

		Stack<Node> stk = new Stack<>();

		stk.push(n);

		while (!stk.isEmpty()) {

			Node tn = stk.pop();
			System.out.println(tn.data);
			if (tn.right != null)
				stk.push(tn.right);
			if (tn.left != null)
				stk.push(tn.left);
		}

	}

	public static void postOrder_itr(Node n) {

		Stack<Node> stk = new Stack<>();

		// pushInStack(n, stk);
		stk.push(n);
		while (!stk.isEmpty()) {
			n = stk.peek();
			while (n.left != null) {
				stk.push(n.left);
				n = n.left;
			}
			System.out.println(stk.pop().data);
			if (stk.isEmpty())
				return;
			Node tn = stk.peek();

			if (tn.right == null || tn.right == n) {
				System.out.println(stk.pop().data);
				if (stk.isEmpty())
					break;
				tn = stk.peek();
			}

			while (tn.right == null) {
				System.out.println(stk.pop().data);
				if (stk.isEmpty())
					break;
				tn = stk.peek();

			}
			
			stk.push(tn.right);
			//n = tn.right;

		}
	}

	public static void inOrder_itr(Node n) {

		Stack<Node> stk = new Stack<>();
		stk.add(n);

		while (!stk.isEmpty()) {
			n = stk.peek();

			while (n.left != null) {
				stk.push(n.left);
				n = n.left;
			}
			n = stk.pop();
			System.out.println(n.data);
			if (stk.isEmpty())
				return;

			while (n.right == null) {
				n = stk.pop();
				System.out.println(n.data);
				break;

			}

			stk.push(n.right);

		}

	}

}
