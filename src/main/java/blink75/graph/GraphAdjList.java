package blink75.graph;



import com.graph.GraphEdge;
import com.graph.Graph_AdjList;

import java.util.*;
import java.util.stream.Collectors;

public class GraphAdjList {

  public Map<Integer, Set<Edge>> adjList = new HashMap<>();
  public boolean biDirectional=false;

  public void addOrUpdateEdge(int srcV, int destV) {
    addOrUpdateEdge(srcV,destV, 1);
  }

  public void addOrUpdateEdge(int srcV, int destV, int val) {

    adjList.putIfAbsent(srcV, new HashSet<>());
    adjList.get(srcV).add(new Edge(srcV, destV, val));

    if (biDirectional) {
      adjList.putIfAbsent(destV, new HashSet<>());
      adjList.get(destV).add(new Edge(destV, srcV, val));
    }

  }

  public List<Edge> getAllEdges() {

    return adjList.values().stream().flatMap(se->se.stream()).collect(Collectors.toList());

  }

  public static GraphAdjList createGraph1() {
    GraphAdjList g = new GraphAdjList();
    //g.biDirectional = true;
    g.addOrUpdateEdge(1, 0);
    g.addOrUpdateEdge(0, 2);
    g.addOrUpdateEdge(2, 1);
    g.addOrUpdateEdge(0, 3);
    g.addOrUpdateEdge(1, 4);
    return g;

  }

  public static GraphAdjList createGraph(){
    GraphAdjList graph = new GraphAdjList();
    graph.biDirectional=true;
    graph.addOrUpdateEdge(0, 9, 4);
    graph.addOrUpdateEdge(0, 1, 7);
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
    graph.addOrUpdateEdge(7, 0, 1);
    return graph;
  }
}
