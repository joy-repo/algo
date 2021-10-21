package com.bst;

import java.util.ArrayList;
import java.util.List;

public class PathFromRootToNode {

	public static void main(String[] args) {
		Node x = new Node(15);
		x.left = new Node(10);
		x.right = new Node(20);
		x.left.left = new Node(8);
		x.left.right = new Node(12);
		x.right.left = new Node(16);
		x.right.right = new Node(25);
		List<Integer> li = new ArrayList<>();
		sol(x, 12, li);
		
		System.out.println(li);
	}

	private static boolean sol(Node n, int i, List<Integer> li) {

		if (n == null)
			return false;

		if (n.data == i) {
			li.add(n.data);
			return true;
		}

		if (n.left != null)
			if (sol(n.left, i,li)) {
				li.add(n.data);
				return true;
			}

		if (n.right != null)
			if (sol(n.right, i,li)) {
				li.add(n.data);
				return true;
			}

		return false;
	}

}
