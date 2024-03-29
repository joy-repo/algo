package com.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {

    static Graph_AdjList graph = Graph_AdjList.createGraph();

    public static void main(String[] args) {

        dfs(0);
        System.out.println();
        System.out.println("------------");
        //System.out.println();
        Set<Integer> visited = new HashSet<>();
        //visited.add(0);
        dfs_Recc(0,visited);
    }

    private static void dfs_Recc(int vert, Set<Integer> visited) {

        visited.add(vert);
        System.out.print(vert+",");
        Set<GraphEdge> edges = graph.adjList.get(vert);
        if(edges==null) edges=new HashSet<>();
        for(GraphEdge e : edges){
            if(!visited.contains(e.destV)) {
                //visited.add(e.destV);
                dfs_Recc(e.destV, visited);
            }
        }
    }


    private static void dfs(int vSrc) {

        Stack<Integer> stk = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(vSrc);
        stk.push(vSrc);

       // visited.add(vSrc);
        while (!stk.isEmpty()){
            Integer vert = stk.pop();
            System.out.print(vert+",");
            Set<GraphEdge> adjEdges = graph.adjList.get(vert);
            if(adjEdges==null) continue;
            for ( GraphEdge e : adjEdges){
               if(!visited.contains(e.destV)){
                   stk.push(e.destV);
                   visited.add(e.destV);
                  // break;
               }
            }


        }


    }
}
