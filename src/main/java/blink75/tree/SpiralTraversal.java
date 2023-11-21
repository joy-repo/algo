package blink75.tree;

import java.util.*;

// [Node{data=1}, Node{data=2}, Node{data=3}, Node{data=5}, Node{data=4}, Node{data=6}, Node{data=7}]
//[Node{data=1}, Node{data=3}, Node{data=2}, Node{data=4}, Node{data=5}, Node{data=7}, Node{data=6}]

public class SpiralTraversal {

  public static Node root = Node.createBT();

  public static void main(String[] args) {
    sol();
  }

  private static void sol() {

    boolean r2l = true;

    List<Node> res = new ArrayList<>();

    Stack<Node> s1 = new Stack<>();
    Stack<Node> s2 = new Stack<>();

    s1.push(root);

    while (!s1.isEmpty() || !s2.isEmpty()) {

      while (!s1.isEmpty()) {
        Node n = s1.pop();
        res.add(n);
        if (n.left != null)
          s2.push(n.left);
        if (n.right != null)
          s2.push(n.right);
      }

      while (!s2.isEmpty()) {
        Node n = s2.pop();
        res.add(n);
        if (n.right != null)
          s1.push(n.right);
        if (n.left != null)
          s1.push(n.left);
      }
    }

    System.out.println(res);


  }
}
