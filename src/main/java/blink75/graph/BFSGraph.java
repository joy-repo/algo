package blink75.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFSGraph {

  static GraphAdjList graphAdjList = GraphAdjList.createGraph();

  public static void main(String[] args) {
    bfs(0);
  }

  private static void bfs(int srcV) {

    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    queue.offer(srcV);
    visited.add(srcV);

    while (!queue.isEmpty()){

      Integer vert =queue.poll();

      System.out.print(vert +",");
      Set<Edge> edges = graphAdjList.adjList.get(vert);

      for( Edge e : edges) {
        if (!visited.contains(e.destV)) {
          queue.offer(e.destV);
          visited.add(e.destV);
        }
      }



    }

  }

}
