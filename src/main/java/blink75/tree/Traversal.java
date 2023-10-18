package blink75.tree;

public class Traversal {

    public static void main(String[] args) {
        Node root =Node.createBT();
        System.out.println("//////// INORDER //////");
        System.out.println();
        inOrder(root); //left root right
        System.out.println();
        System.out.println("//////// PREORDER //////");
        System.out.println();
        preOrder(root); // root left right
        System.out.println();
        System.out.println("//////// PREORDER //////");
        System.out.println();
        postOrder(root); // left right root
    }

    private static void postOrder(Node root) {
        if(root==null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data +"  ");

    }

    private static void preOrder(Node root) {
        if(root==null) return;
        System.out.print(root.data +"  ");
        postOrder(root.left);
        postOrder(root.right);

    }

    private static void inOrder(Node root) {
        if(root==null) return;
        postOrder(root.left);
        System.out.print(root.data +"  ");
        postOrder(root.right);

    }
}
