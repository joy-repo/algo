package blink75.tree;

public class LCABinaryTree {

  static Node root = Node.createBT();

  //              1
  //       2             3
  //    4      5       11  12
  //  8  9    6  7

  static int NODE1= 9;
  static int NODE2= 6;

  public static void main(String[] args) {
    Node node =solRecc(root);
    System.out.println(node);

  }

  private static Node solRecc(Node node) {

    if(node==null) return null;

    if(node.data==NODE1||node.data==NODE2) return node;

    Node lh = solRecc(node.left);
    Node rh = solRecc(node.right);


    if(rh!=null && lh!=null) return node;

    if(rh!=null ) return rh;
    if(lh!=null) return lh;
    return  null;







  }

}
