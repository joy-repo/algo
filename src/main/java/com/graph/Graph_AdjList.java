package com.graph;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph_AdjList {

	public Map<Integer, Set<Edge>> adjList;
	public boolean biDirectional;

	public void addOrUpdateEdge(int srcV, int destV, int val) {
		if (adjList.containsKey(srcV))
			adjList.get(srcV).add(new Edge(srcV, destV, val));
		else {
			Set<Edge> se = new HashSet<>();
			se.add(new Edge(srcV, destV, val));
			adjList.put(srcV, se);
		}
		if (biDirectional)
			if (adjList.containsKey(destV))
				adjList.get(destV).add(new Edge(destV, srcV, val));
			else {
				Set<Edge> se = new HashSet<>();
				se.add(new Edge(destV, srcV, val));
				adjList.put(destV, se);
			}
	}
	
	public List<Edge> getAllEdges() {
		
		return adjList.values().stream().flatMap(se->se.stream()).collect(Collectors.toList());
		
	}

}
