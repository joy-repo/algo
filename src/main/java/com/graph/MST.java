package com.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MST {

	static Graph_Array graph;
	static SubSet[] ss;

	public static void main(String[] args) {
		graph = new Graph_Array(9, true);
		graph.addOrUpdateEdge(0, 1, 4);
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

		// primsMST(0);
		kruskalsMST();
	}

	private static void primsMST(int i) {

		Set<Integer> vSet = new HashSet<Integer>();
		vSet.add(i);
		List<Edge> edgeList = new ArrayList<>();

		for (int v = 0; v < graph.numOfvertices - 1; v++) {
			edgeList.add(getSmallestEddge(vSet));
		}
		edgeList.forEach(System.out::println);
		System.out.println(edgeList.stream().map(e -> e.val).mapToInt(f -> f).sum());

		// int y = edgeList.stream().map(e->e.val).reduce((i1,i2)-> i1+i2).get();

	}

	private static Edge getSmallestEddge(Set<Integer> vSet) {

		int vSrc = 0, vDest = 0, val = Integer.MAX_VALUE;

		for (int c : vSet) {
			for (int r = 0; r < graph.numOfvertices; r++) {
				if (!vSet.contains(r) && graph.graph[c][r] != 0 && val > graph.graph[c][r]) {
					val = graph.graph[c][r];
					vSrc = c;
					vDest = r;
				}
			}
		}
		vSet.add(vDest);
		return new Edge(vSrc, vDest, val);

	}

	private static void kruskalsMST() {
		ss = new SubSet[graph.numOfvertices];
		List<Edge> edgeList = graph.getAllEdges();
		List<Edge> MSTedgeList = new ArrayList<Edge>();
		edgeList.sort((e1, e2) -> e2.val - e1.val);

		for (int v = 0; v < graph.numOfvertices - 1; v++) {
			int cnt = 0;
			while (true) {
				Edge e = edgeList.get(cnt);
				if (find(e.srcV) != find(e.destV)) {
					MSTedgeList.add(e);
					union(e.srcV, e.destV);
					edgeList.remove(cnt);
					break;
				}
				cnt++;
			}

			MSTedgeList.forEach(System.out::println);
		}

	}

	private static int find(int v) {
		if (ss[v].parent != v)
			return find(ss[v].parent);
		return v;
	}

	private static void union(int v1, int v2) {
		if (ss[v1].rank > ss[v2].rank)
			ss[v1].parent = ss[v2].parent;
		else
			ss[v2].parent = ss[v1].parent;
	}

	static class SubSet {

		int parent = 0, rank = 0;
	}

}
