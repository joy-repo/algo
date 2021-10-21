package com.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph_Array {

	/// graph[src][des] ---- columns source--- rows dest
	public int graph[][];
	public int numOfvertices;
	public boolean biDirectional;

	public Graph_Array(int numOfvertices, boolean biDirectional) {
		graph = new int[numOfvertices][numOfvertices];
		this.numOfvertices = numOfvertices;
		this.biDirectional = biDirectional;
	}

	public void addOrUpdateEdge(int srcV, int destV, int val) {
		graph[srcV][destV] = val;
		if (biDirectional)
			graph[destV][srcV] = val;
	}

	public List<Edge> getAllEdges() {

		List<Edge> lEdge = new ArrayList<>();
		for (int c = 0; c < graph.length; c++)
			for (int r = 0; r < graph[0].length; r++)
				if (graph[c][r] != 0)
					lEdge.add(new Edge(c, r, graph[c][r]));

		return lEdge;

	}

}


