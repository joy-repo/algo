package com.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFS {

    static Graph_AdjList graph = Graph_AdjList.createGraph1();

    public static void main(String[] args) {

        dfs(0);
        System.out.println();
        System.out.println("------------");
        //System.out.println();
        Set<Integer> visited = new HashSet<>();

        dfs_Recc(0,visited);
    }

    private static void dfs_Recc(int vert, Set<Integer> visited) {

        visited.add(vert);
        System.out.print(vert+",");
        Set<Edge> edges = graph.adjList.get(vert);
        if(edges==null) edges=new HashSet<>();
        for(Edge e : edges){
            if(!visited.contains(e.destV)) {
                visited.add(e.destV);
                dfs_Recc(e.destV, visited);
            }
        }
    }


    private static void dfs(int vSrc) {

        Stack<Integer> stk = new Stack<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(vSrc);
        stk.push(vSrc);

        visited.add(vSrc);
        while (!stk.isEmpty()){
            Integer vert = stk.pop();
            System.out.print(vert+",");
            Set<Edge> adjEdges = graph.adjList.get(vert);
            if(adjEdges==null) continue;
            for ( Edge e : adjEdges){
               if(!visited.contains(e.destV)){
                   stk.push(e.destV);
                   visited.add(e.destV);
                  // break;
               }
            }


        }


    }
}
