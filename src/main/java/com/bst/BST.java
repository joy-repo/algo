package com.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BST {

	Node root;

	public void insert(int data, Node n) {
		if (root == null) {
			root = new Node(data);
			return;
		}
		if (data > n.data)
			if (n.right == null)
				n.right = new Node(data);
			else
				insert(data, n.right);

		if (data < n.data)
			if (n.left == null)
				n.left = new Node(data);
			else
				insert(data, n.left);
	}

	public Node search(Node n, int data) {

		if (n == null)
			return null;

		if (data == n.data)
			return n;

		if (data > n.data)
			return search(n.right, data);
		return search(n.left, data);
	}

	public Node delete(Node n, Node d) {

		if (n == null)
			return n;

		if (n.data < d.data)
			n.right = delete(n.right, d);
		else if (n.data > d.data)
			n.left = delete(n.left, d);
		else {
			if (n.right == null)
				return n.left;
			if (n.left == null)
				return n.right;
			Node min_n = minNode(n);
			delete(n, min_n);
			n.data = min_n.data;
		}
		return n;

	}

	public Node minNode(Node n) {

		if (n.left == null)
			return n;
		return minNode(n.left);
	}
	
	public int size(Node n) {
		
		if(n==null) return 0;
		return size(n.right)+size(n.left)+1;
	}
	
	public int size_iterative(Node n) {
		
		Queue<Node> q = new LinkedList<Node>();
		int c=0;
		q.offer(n);
		while(!q.isEmpty()) {
			Node t =q.poll();
			c++;
			if(t.right!=null) q.offer(t.right);
			if(t.left!=null) q.offer(t.left);
		}
		return c;
	}
	
	public int height(Node n) {
		
		if(n==null) return 0;
		return Math.max(height(n.right)+1,size(n.left)+1);
	}
	
	public int height_itr(Node n) {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(n);
		int c=0;
		while(!q.isEmpty()) {
			c++;
			int size=q.size();
			while(size>0){
				Node t= q.poll();
				if(t.right!=null) q.offer(t.right);
				if(t.left!=null) q.offer(t.left);
				size--;
			}
		}
		return c;
		
	}

}
