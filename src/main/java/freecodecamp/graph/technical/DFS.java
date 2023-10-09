package freecodecamp.graph.technical;

import java.util.*;

public class DFS {



    public static void main(String[] args) {
        Graph g = new Graph();
        g.addEdge("s", "a");
        g.addEdge("s", "b");
        g.addEdge("s", "c");
        g.addEdge("a", "d");
        g.addEdge("b", "e");
        g.addEdge("c", "f");
        g.addEdge("d", "g");
        g.addEdge("e", "g");
        g.addEdge("f","g");

        dfs(g,"s");
        System.out.println("------------------Reccursion-----");

        dfs_rec(g,new Node("s"), new HashSet<>());
    }

    private static void dfs_rec(Graph g,  Node n, Set<Node> visited ) {

        if(n==null) return;
        if(!visited.contains(n)) {
            System.out.println(n.name);
            visited.add(n);
        }

        for(Node t : g.adjList.get(n)){
            dfs_rec(g, t, visited);
//            visited.add(t);
        }

    }

    private static void dfs(Graph g, String s) {
        Stack<Node> stk = new Stack<>();
        Set<Node> visited = new HashSet<>();
        stk.push(new Node(s));
        visited.add(new Node(s));
        while(!stk.isEmpty()){
            Node n = stk.pop();
            System.out.println(n.name);
            visited.add(n);
            Set<Node> adjNodes =g.adjList.get(n);
            for(Node t : adjNodes){
                if(!visited.contains(t)){
                    //System.out.println(n.name);
                    stk.push(t);
                }
            }
        }

    }
}
