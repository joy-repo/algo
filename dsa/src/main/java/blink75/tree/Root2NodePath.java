package blink75.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Root2NodePath {

  static Node root = Node.createBT();

  //              1
  //       2             3
  //    4      5       11  12
  //  8  9    6  7

  static int NODE_DATA = 6;

  static List<Node> RESLIST = new ArrayList<>();

  public static void main(String[] args) {
    //List<Node> path = new ArrayList<>()
    List<Node> res =solRecc(root, new ArrayList<>() );
    System.out.println(res);

    solRecc2(root);
    System.out.println(RESLIST);
  }

  public static boolean solRecc2(Node node){

    if(node==null) return false;

    RESLIST.add(node);

    if(node.data==NODE_DATA)
      return true;

    if(solRecc2(node.left)) return true;
    if(solRecc2(node.right)) return true;
    RESLIST.remove(node);
    return false;
  }



  private static List<Node> solRecc(Node node, List<Node> path) {

    if(node == null) return Collections.EMPTY_LIST;
    path.add(node);

    if(node.data==NODE_DATA){
      return path;
    }

    List<Node> res1 = solRecc(node.left, path);
    if(res1.size()>=1) return res1;

    List<Node> res2 = solRecc(node.right, path);
    if(res2.size()>=1) return res2;

    path.remove(node);

    return Collections.EMPTY_LIST;




  }
}
