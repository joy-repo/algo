package com.graph;

import java.util.*;

public class BFS {

    static Graph_AdjList graph = Graph_AdjList.createGraph();

    public static void main(String[] args) {
        bfs(0);
    }

    private static void bfs(int vSrc) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(vSrc);
        queue.offer(vSrc);

        visited.add(vSrc);
        while (!queue.isEmpty()){
            Integer vert = queue.poll();
            System.out.print(vert+",");
            Set<GraphEdge> adjEdges = graph.adjList.get(vert);
            if(adjEdges==null) continue;
            for ( GraphEdge e : adjEdges){
                if(!visited.contains(e.destV)){
                    queue.offer(e.destV);
                    visited.add(e.destV);
                    // break;
                }
            }


        }
    }
}
