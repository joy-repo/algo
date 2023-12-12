package com.graph;

import java.util.*;

public class TopologicalSort {
    //https://www.geeksforgeeks.org/topological-sorting/

//    static int[][] GRAPH = {
//            {0,0,0,0,0,0},
//            {0,0,0,0,0,0},
//            {0,0,0,1,0,0},
//            {0,1,0,0,0,0},
//            {1,1,0,0,0,0},
//            {1,0,1,0,0,0}
//    };

    static int[][] GRAPH = {
            {0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0},
            {1, 0, 1, 0, 0, 0}
    };

    public static void main(String[] args) {
      //  topologicalSort_NON_REC();


        for(int i=0; i< GRAPH.length;i++) {
            List aa =new ArrayList<>();
            aa.add(i);
            allTopologicalSorts_REC(i, aa);


        }

    }

    private static void allTopologicalSorts_REC(int vertex, List<Integer> res) {

        if (res.size() == GRAPH.length) {
            System.out.println(Arrays.toString(res.toArray()));
            return;
        }
        if(vertex==-1){
            vertex=getUnvisitedVertex(res);
        }

        int[] adjVertex = GRAPH[vertex];
        boolean b = false;
        for (int i = 0; i < adjVertex.length; i++) {
            if (adjVertex[i] == 1 && !res.contains(i)) {
                b=true;
                res.add(i);
                allTopologicalSorts_REC(i, res);
                res.remove(res.indexOf(i));

            }
        }
        allTopologicalSorts_REC(-1, res);

    }

    private static void printUltaOrder(List<Integer> res) {

        for (int i = res.size() - 1; i >= 0; i--)
            System.out.print(res.get(i) + ",");
        System.out.println();
    }


    private static void topologicalSort_NON_REC() {

        //Stack<Integer> visited = new Stack<>();
        Stack<Integer> res = new Stack<>();


        while (res.size() < GRAPH.length) {
            int vertex = getUnvisitedVertex(res);
            if (vertex == -1) break;
            //visited.add(vertex);
            explore(vertex, res);
        }
        while (!res.isEmpty())
            System.out.println(res.pop());
    }

    private static void explore(int vertex, Stack<Integer> res) {

        //int[] adjVertex = GRAPH[vertex];
        Stack<Integer> tempST = new Stack<>();
        tempST.push(vertex);
        boolean isChildAvailable = true;
        while (!tempST.isEmpty()) {
            isChildAvailable = false;
            int tVertex = tempST.peek();
            int[] adjVertex = GRAPH[tVertex];

            for (int i = 0; i < adjVertex.length; i++) {
                if (adjVertex[i] == 1 && !res.contains(i)) {
                    tempST.push(i);
                    isChildAvailable = true;
                }
            }
            if (!isChildAvailable) {
                res.push(tempST.pop());
            }
        }
    }

    private static int getUnvisitedVertex(Collection<Integer> res) {
        for (int i = 0; i < GRAPH.length; i++) {
            if (!res.contains(i)) return i;
        }
        return -1;
    }


}
