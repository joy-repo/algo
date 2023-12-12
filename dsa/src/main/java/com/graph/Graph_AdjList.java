package com.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph_AdjList {

	public Map<Integer, Set<GraphEdge>> adjList = new HashMap<>();
	public boolean biDirectional=false;

	public void addOrUpdateEdge(int srcV, int destV ){
		addOrUpdateEdge(srcV,destV,1);
	}

	public void addOrUpdateEdge(int srcV, int destV, int val) {
		if (adjList.containsKey(srcV))
			adjList.get(srcV).add(new GraphEdge(srcV, destV, val));
		else {
			Set<GraphEdge> se = new HashSet<>();
			se.add(new GraphEdge(srcV, destV, val));
			adjList.put(srcV, se);
		}
		if (biDirectional)
			if (adjList.containsKey(destV))
				adjList.get(destV).add(new GraphEdge(destV, srcV, val));
			else {
				Set<GraphEdge> se = new HashSet<>();
				se.add(new GraphEdge(destV, srcV, val));
				adjList.put(destV, se);
			}
	}
	
	public List<GraphEdge> getAllEdges() {
		
		return adjList.values().stream().flatMap(se->se.stream()).collect(Collectors.toList());
		
	}

	public static Graph_AdjList createGraph1() {
		Graph_AdjList g = new Graph_AdjList();
		//g.biDirectional = true;
		g.addOrUpdateEdge(1, 0);
		g.addOrUpdateEdge(0, 2);
		g.addOrUpdateEdge(2, 1);
		g.addOrUpdateEdge(0, 3);
		g.addOrUpdateEdge(1, 4);
		return g;

	}

	public static Graph_AdjList createGraph(){
		Graph_AdjList graph = new Graph_AdjList();
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
		return graph;
	}

}
