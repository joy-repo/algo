package blink75.tree;

public class IsBalanced {

  public static Node root = Node.createBT();

  public static void main(String[] args) {
   int res = solRecc(root);
   System.out.println(res);
  }

  private static int solRecc(Node root) {

    if(root==null) return 0;

    int lh = solRecc(root.left);
    if(lh==-1) return -1;
    int rh = solRecc(root.right);
    if(rh==-1) return -1;

    if (Math.abs(lh-rh)>1) return  -1;
    return Math.max(lh,rh)+1;



  }
}
