package com.bst;

import java.util.Stack;

public class IdenticalTree {

	public static void main(String[] args) {

		// construct first tree
		Node x = new Node(15);
		x.left = new Node(10);
		x.right = new Node(20);
		x.left.left = new Node(8);
		x.left.right = new Node(12);
		x.right.left = new Node(16);
		x.right.right = new Node(25);

		// construct second tree
		Node y = new Node(15);
		y.left = new Node(10);
		y.right = new Node(20);
		y.left.left = new Node(8);
		y.left.right = new Node(12);
		y.right.left = new Node(16);
		y.right.right = new Node(25);
	}

	public static boolean sol_rec(Node n1, Node n2) {

		if (n1 == null && n2 == null)
			return true;

		if (n1 == null)
			return false;
		if (n2 == null)
			return false;

		if (n1.data != n2.data)
			return false;

		return (n1.data == n2.data) && sol_rec(n1.left, n2.left) && sol_rec(n1.right, n2.right);

	}

	public static boolean sol_itr(Node n1, Node n2) {

		Stack<Pair> stk = new Stack<>();
		stk.add(new Pair(n1, n2));

		while (!stk.isEmpty()) {
			Pair p = stk.pop();

			if (p.n1.data != p.n2.data)
				return false;

			if (p.n1.left != null && p.n2.left != null)
				stk.add(new Pair(p.n1.left, p.n2.left));
			else if (p.n1.left != null || p.n2.left != null)
				return false;

			if (p.n1.right != null && p.n2.right != null)
				stk.add(new Pair(p.n1.right, p.n2.right));
			else if (p.n1.right != null || p.n2.right != null)
				return false;

		}
		
		return true;

	}

	public static class Pair {
		public Node n1;
		public Node n2;

		public Pair(Node n1, Node n2) {
			this.n1 = n1;
			this.n2 = n2;
		}
	}
}
