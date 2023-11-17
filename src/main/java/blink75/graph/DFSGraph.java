package blink75.graph;

import java.util.*;

public class DFSGraph {

  static GraphAdjList graphAdjList = GraphAdjList.createGraph();

  public static void main(String[] args) {
    dfs(0);
  }

  private static void dfs(int srcV) {

    Stack<Integer> stk = new Stack<>();
    Set<Integer> visited = new HashSet<>();
    stk.push(srcV);
    visited.add(srcV);

    while (!stk.isEmpty()){

      Integer vert =stk.pop();

      System.out.print(vert +",");
      Set<Edge> edges = graphAdjList.adjList.get(vert);

      for( Edge e : edges) {
        if (!visited.contains(e.destV)) {
          stk.push(e.destV);
          visited.add(e.destV);
        }
      }



    }

  }

}
