package com.bst;

public class NodesAtKDistance_OLD_NOT_DONE {

	public static void main(String[] args) {

		BinaryTree1 tree = new BinaryTree1();

		/* Let us construct the tree shown in above diagram */
		tree.root = new Node(20);
		tree.root.left = new Node(8);
		tree.root.right = new Node(22);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(12);
		tree.root.left.right.left = new Node(10);
		tree.root.left.right.right = new Node(14);

		Node target = tree.root.left;
		int distance = 2;

		findBelow(target, 2);
	}



	private static void findBelow(Node target, int k) {

		if (k == 0) {
			System.out.println(target.data);
			return;
		}

		if (target == null)
			return;

		if (target.right != null)
			findBelow(target.right, k - 1);
		if (target.left != null)
			findBelow(target.left, k - 1);

	}

	/*
	 * private static Node findKDistanceNodes(Node root, Node target, int d) {
	 * 
	 * if (target != root) {
	 * 
	 * if (root == null) return null;
	 * 
	 * if (root.right != null) return findKDistanceNodes(root.right, target, d+1);
	 * if (root.left != null) return findKDistanceNodes(root.left, target, d+1); }
	 * 
	 * 
	 * 
	 * }
	 */

}
