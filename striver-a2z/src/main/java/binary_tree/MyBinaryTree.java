package binary_tree;

public class MyBinaryTree {

    public static void main(String[] args) {
        Node rroott =generateBinaryTree();

        System.out.println(rroott);
    }


    //  public Node root;

//                  10
//            8            5
//         6    9      2       18
//      3    7           14  17
//                              21

    public static Node generateBinaryTree1() {

        Node root = new Node(1);
        root.left = new Node(2);
        root.right= new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);
        root.right.left= new Node(6);
        return root;
    }

    public static Node generateBinaryTree() {

        Node root = new Node(10);
        root.left = new Node(8);
        root.right = new Node(5);
        root.left.left = new Node(6);
        root.left.right = new Node(9);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(7);

        root.right.left = new Node(2);
        root.right.left.right = new Node(14);
        root.right.right = new Node(18);
        root.right.right.left = new Node(17);
        root.right.right.left.right = new Node(21);

        return root;


    }

    public static class Node {
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

}
