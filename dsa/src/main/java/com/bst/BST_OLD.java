package com.bst;

import java.util.ArrayList;
import java.util.List;

public class BST_OLD {

	public Node root;

	public void getPath(Node n, int searchData, String str) {

		if (n == null)
			return ;

		if (n.data == searchData) {
			System.out.println(str);
			return ;
		}
		
		getPath(n.left, searchData, str+"-"+n.data);
		getPath(n.right, searchData, str+"-"+n.data);
	}
	
	public String getPath(Node n, int searchData) {
		
		if (n == null)
			return null;
		
		if (n.data == searchData) {
			//System.out.println(str);
			return n.data+"" ;
		}
		String tStr = getPath(n.left, searchData);
		
		if(tStr!=null)
			return n.data+"-"+tStr;
		
		tStr = getPath(n.right, searchData);
		
		if(tStr!=null)
			return n.data+"-"+tStr;
		
		return null;
	}
	
public List<Node> getPath_(Node n, int searchData) {
		
		if (n == null)
			return null;
		
		if (n.data == searchData) {
			List<Node> l = new ArrayList<>();
			l.add(n);
			return l;
		}
		List<Node> ltemp = getPath_(n.left, searchData);
		
		if(ltemp!=null) {
			 ltemp.add(n);
			 return ltemp;
		}
		
		ltemp = getPath_(n.right, searchData);
		
		if(ltemp!=null) {
			 ltemp.add(n);
			 return ltemp;
		}
		
		return null;
	}

}
