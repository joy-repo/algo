package blink75.tree;

public class MaxPathSum {

  public static Node root = Node.createBT();
  public static int ANS =Integer.MIN_VALUE;
  public static void main(String[] args) {
    solRecc(root);
    System.out.println("Ans :"+  ANS);
  }

  private static int solRecc(Node root) {

    if(root==null) return 0;

    int lSum = solRecc(root.left);

    int rSum = solRecc(root.right);

    int res = (rSum+lSum)+root.data;

    ANS = Math.max(ANS, res);

    return Math.max(rSum,lSum)+root.data;
  }
}
