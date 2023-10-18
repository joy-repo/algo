package blink75.tree;


public class Node {
    
    public int data;
    public Node right;
    public Node left;

    public Node(){}
    public Node(int data){
        this.data=data;
    }
    public static Node createBT(){
        Node root = new Node(1);
        root.left= new Node(2);
        root.right = new Node(3);
        root.left.left= new Node(4);
        root.left.right = new Node(5);
        root.left.right.left= new Node(6);
        root.left.right.right=new Node(7);
        return root;
    }
}
