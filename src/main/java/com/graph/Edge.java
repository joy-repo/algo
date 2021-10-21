package com.graph;

public class Edge {
	public int srcV;
	public int destV;
	public int val;

	public Edge(int srcV, int destV, int val) {
		super();
		this.srcV = srcV;
		this.destV = destV;
		this.val = val;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + destV;
		result = prime * result + srcV;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (destV != other.destV)
			return false;
		if (srcV != other.srcV)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [srcV=" + srcV + ", destV=" + destV + ", val=" + val + "]";
	}
	
	

}