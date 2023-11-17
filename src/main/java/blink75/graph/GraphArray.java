package blink75.graph;




import java.util.ArrayList;
import java.util.List;

public class GraphArray {

  /// graph[src][des] ---- columns source--- rows dest
  public int graph[][];
  public int numOfvertices;
  public boolean biDirectional;

  public GraphArray(int numOfvertices, boolean biDirectional) {
    graph = new int[numOfvertices][numOfvertices];
    this.numOfvertices = numOfvertices;
    this.biDirectional = biDirectional;
  }

  public void addOrUpdateEdge(int srcV, int destV, int val) {
    graph[srcV][destV] = val;
    if (biDirectional)
      graph[destV][srcV] = val;
  }

  public List<Edge> getAllEdges() {

    List<Edge> lEdge = new ArrayList<>();
    for (int c = 0; c < graph.length; c++)
      for (int r = 0; r < graph[0].length; r++)
        if (graph[c][r] != 0)
          lEdge.add(new Edge(c, r, graph[c][r]));

    return lEdge;

  }

  public static GraphArray createGraph(){

    GraphArray graph = new GraphArray(9, true);
    graph.addOrUpdateEdge(0, 1, 4);
    graph.addOrUpdateEdge(0, 7, 8);
    graph.addOrUpdateEdge(1, 2, 8);
    graph.addOrUpdateEdge(1, 7, 11);
    graph.addOrUpdateEdge(2, 3, 7);
    graph.addOrUpdateEdge(2, 5, 4);
    graph.addOrUpdateEdge(2, 8, 2);
    graph.addOrUpdateEdge(3, 4, 9);
    graph.addOrUpdateEdge(3, 5, 14);
    graph.addOrUpdateEdge(4, 5, 10);
    graph.addOrUpdateEdge(5, 6, 2);
    graph.addOrUpdateEdge(6, 7, 1);
    graph.addOrUpdateEdge(6, 8, 6);
    graph.addOrUpdateEdge(7, 8, 7);
    return graph;

  }
}
