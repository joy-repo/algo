package binary_tree;

import binary_tree.MyBinaryTree.Node;
import com.itextpdf.awt.geom.gl.Crossing.QuadCurve;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeSet;

public class Medium_10_BottomView {

    public static void main(String[] args) {
        Node node = MyBinaryTree.generateBinaryTree();

        getBottomView(node);

    }

    private static void getBottomView(Node node) {

        Queue<Pair> qu = new LinkedList<>();

        qu.offer(new Pair(node, 0));
        Map<Integer, Node> map = new HashMap<>();
        map.put(0, node);

        while (!qu.isEmpty()){
            Pair p = qu.poll();
            map.put(p.horiIndex, p.node);
            if(p.node.left!=null){
                qu.offer(new Pair(p.node.left, p.horiIndex-1));
            }
            if(p.node.right!=null){
                qu.offer(new Pair(p.node.right, p.horiIndex+1));
            }
        }

        TreeSet<Integer> treeSet = new TreeSet<>(map.keySet());
        for(Integer i : treeSet){
           System.out.print(map.get(i));
        }

    }

    @ToString
    static class Pair{
        public Node node;
        public int horiIndex;

        public Pair(Node node, int horiIndex){
            this.node=node;
            this.horiIndex=horiIndex;
        }

    }
}
