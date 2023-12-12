package blink75.tree;

import java.util.*;

public class TopView {

  public static Node root = Node.createBT();
  public static TreeMap<Integer, TreeMap<Integer, List<Node>>> map = new TreeMap<>();
  /// Horizntal -> vertical , List<Node>

  public static void main(String[] args) {
    solRecc(root, 0, 0);
    System.out.println(map);
    for(Map.Entry<Integer, TreeMap<Integer, List<Node>>> e : map.entrySet()){

      int key = e.getValue().firstKey();

      System.out.println(e.getKey() + ":" +e.getValue().get(key).get(0));
    }
  }

  private static void solRecc(Node root, int vertical, int horizontal) {

    if(root==null) return;

    map.putIfAbsent(horizontal, new TreeMap<>());
    map.get(horizontal).putIfAbsent(vertical,new ArrayList<>());
    map.get(horizontal).get(vertical).add(root);

    solRecc(root.left, vertical+1, horizontal-1);

    solRecc(root.right, vertical+1, horizontal+1);
  }
}
