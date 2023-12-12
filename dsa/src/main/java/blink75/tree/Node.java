package blink75.tree;


public class Node {
    
    public int data;

    @Override
    public String toString() {
        return "Node{" +
            "data=" + data +
            "}";
    }

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
        root.right.left = new Node(11);
        root.right.right = new Node(12);
        root.left.left= new Node(4);
        root.left.left.left= new Node(8);
        root.left.left.right= new Node(9);
        root.left.right = new Node(5);
        root.left.right.left= new Node(6);
        root.left.right.right=new Node(7);
        return root;
    }

    //              1
    //       2             3
    //    4      5       11  12
    //  8  9    6  7
}

//[Node{data=1}, Node{data=3}, Node{data=2}, Node{data=4}, Node{data=5}, Node{data=11}, Node{data=12}, Node{data=7}, Node{data=6}, Node{data=9}, Node{data=8}]