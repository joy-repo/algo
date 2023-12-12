package com.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrintPathBetween2Nodes {

    static Node  ROOT = BinaryTree.generateBinaryTree();

    public static void main(String[] args) {

        List<Integer> res = path2Nodes(21, 2);
//        int res = findLevel(9,ROOT);
        System.out.println("-----------");
        if (res == null) return;
        //res.stream().forEach(System.out::print);
        System.out.println(res);

    }

    private static List<Integer> path2Nodes(int d1, int d2) {
//
//        int level1 = findLevel(d1, ROOT);
//        int level2 = findLevel(d2, ROOT);
//
//        System.out.println(level1);
//        System.out.println(level2);
//
//        if (level1==level2) return Collections.emptyList();
//
 //       List<Integer> result1 = new ArrayList<>();
//        int high = level1>level2?d1:d2;
//        int low = level2>level1?d1:d2;

      //  Node n1 = getNode(ROOT,d1);
     //  List<Integer> res1 =  getPathUtil(nLow,high,result);
    //   if(res1!=null)  return res1;
      //  Node n2 = getNode(ROOT,d2);
        List<Integer> result1 = new ArrayList<>();
        List<Integer> res1=getPathUtil(ROOT,d1,result1);
        List<Integer> result2 = new ArrayList<>();
        List<Integer> res2=getPathUtil(ROOT,d2,result2);
        int i=0;
        for( i=0; i<Math.min(res1.size(),res2.size()); ) {
            if (res1.get(i) != res2.get(i)) break;
            i++;
        }
        //List<Integer> tt = res2.subList(i,res2.size()-1);
        i=i-1;

        List<Integer> ll = res2.subList(i,res2.size());

        List<Integer> ff = res1.subList(i+1,res1.size());
        Collections.reverse(ff);
        ff.addAll(ll);

        return ff;

      //  return Collections.emptyList();


    }

    private static Node getNode(Node n, int low) {

        if(n==null) return null;
        if(n.data==low) return n;

       Node t = getNode(n.left,low);
       if(t!=null) return t;
       t = getNode(n.right,low);
       return t;


    }

    private static List<Integer> getPathUtil(Node node, int data, List<Integer> result) {

        if(node==null) return null;
        if(node.data==data){
            result.add(data);
            return result;
        }
        result.add(node.data);
        List<Integer> temp = getPathUtil(node.left,data,result);
        if(temp!=null) return temp;
        temp =getPathUtil(node.right,data,result);
        if(temp!=null) return temp;
        result.remove(result.lastIndexOf(node.data));
        return null;


    }

    private static int findLevel(int d, Node n) {

        if(n==null) return -100;

        if(n.data==d)
            return 0;

        int ll = findLevel(d, n.left);
        int rr = findLevel(d, n.right);
        int res = (ll!=-100?ll:rr);
        return res==-100?res:res+1;

    }
}
