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
		//sol1(x, 12, li);
		System.out.println(li);
		System.out.println(sol1(x, 12, new ArrayList<>()));
	}

	private static List<Integer> sol1(Node n, int target, List<Integer> li) {

		if (n == null) return null;
		if (n.data == target) {
			li.add(n.data);
			return li;
		}

		li.add(n.data);
		List<Integer> res = sol1(n.right, target, li);
		if (res != null) return res;
		res = sol1(n.left, target, li);
		if (res != null) return res;
		li.remove(li.lastIndexOf(n.data));
		return null;


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
