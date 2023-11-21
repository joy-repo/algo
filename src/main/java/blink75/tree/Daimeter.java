package blink75.tree;

public class Daimeter {

  public static Node root = Node.createBT();
  public static int ANS=-1;

  public static void main(String[] args) {
    solRecc(root);
    System.out.println("ANS :"+ ANS);
  }

  private static int solRecc(Node root) {

    if(root==null) return 0;

    int lh = solRecc(root.left);

    int rh = solRecc(root.right);
    ANS = Math.max(ANS, lh+rh);

    return Math.max(lh,rh)+1;



  }
}
