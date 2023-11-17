package blink75.graph;


// https://bit.ly/3oekoir
public class RottenOranges {

  private static GraphAdjList graph = createRottenOrangesGraph();

  private static GraphAdjList createRottenOrangesGraph(){
    GraphAdjList graph = new GraphAdjList();


    graph.addOrUpdateEdge(0, 9, 4);
    graph.addOrUpdateEdge(0, 9, 4);
  }


}
