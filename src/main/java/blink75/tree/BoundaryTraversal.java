package blink75.tree;

import java.util.ArrayList;
import java.util.List;

public class BoundaryTraversal {

  public static Node root = Node.createBT();

  //              1
  //       2             3
  //    4      5       11  12
  //  8  9    6  7


  /// res -> 1, 2, 4, 8,9,6,7,11,12,3
  public static List<Node> res = new ArrayList<>();

  public static int N=10;

  public static void main(String[] args) {

   // print1toN(1);


    //Print the Left
   solReccLeft(root);
    /// Print the leaf
    solReccLeaf(root);

    /// Print Right in reverse
    solReccRightReverse(root);

    System.out.println(res);
  }

  private static void print1toN(int i) {

    if(i>N) return;


    print1toN(i+1);
    System.out.println(i);
  }

  private static void solReccRightReverse(Node node) {

    if(node==null) return ;

    if(isLeaf(node)) return ;

    if(node.right!=null){
      solReccLeft(node.right);
    } else {
      solReccLeft(node.left);
    }
    res.add(node);

  }

  private static void solReccLeaf(Node node) {

    if(node==null) return;

    if(isLeaf(node)) {
      res.add(node);

    }

    solReccLeaf(node.left);
    solReccLeaf(node.right);


  }

  private static void solReccLeft(Node node) {

    if(node==null) return;

    if(isLeaf(node)) return;

    res.add(node);

    if(node.left!=null){
      solReccLeft(node.left);
    } else {
      solReccLeft(node.right);
    }

  }

  private static boolean isLeaf(Node root) {
    if(root.right==null && root.left==null) return true;
    return false;
  }

}
