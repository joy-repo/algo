package com.bst;

public class Node {
    public Node right;
    public Node left;
    public int data;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" + data + "}";
    }
}
