package freecodecamp.graph.technical;

import java.util.*;

public class Graph {

    Map<Node,Set<Node>> adjList = new HashMap<>();

    public void removeEdge(Node n1, Node n2){
        if(adjList.get(n1).contains(n2)){
            adjList.get(n1).remove(n2);
        }
    }

    public void addEdge(String n1, String n2){
        if(!adjList.containsKey(new Node(n1))) addNode(n1);
        if(!adjList.containsKey(new Node(n2))) addNode(n2);
        adjList.get(new Node(n1)).add(new Node(n2));
    }
    public void addEdge(Node n1, Node n2){
        if(!adjList.containsKey(n1)) addNode(n1);
        if(!adjList.containsKey(n2)) addNode(n2);
        adjList.get(n1).add(n2);
    }

    public void addNode(Node n){
        adjList.put(n, new HashSet<>());
    }

    public void addNode(String nodeName){
        adjList.put(new Node(nodeName), new HashSet<>());
    }


}

class Node{
    public String name;

    public Node(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}