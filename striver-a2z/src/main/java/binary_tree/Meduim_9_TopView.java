package binary_tree;

import binary_tree.MyBinaryTree.Node;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Meduim_9_TopView {

    public static void main(String[] args) {
        Node node = MyBinaryTree.generateBinaryTree();

        getTopview(node);
    }

    private static void getTopview(Node node) {

        Queue<Pair> qu1 = new LinkedList<>();
       // Queue<Pair> qu2 = new LinkedList<>();

        qu1.offer(new Pair(node, 0));

        Set<Integer> horiIndexSet = new HashSet<>();
        List<Pair> res = new ArrayList<>();

        horiIndexSet.add(0);
        res.add(new Pair(node,0));

        while (!qu1.isEmpty()){

            while (!qu1.isEmpty()){
                Pair p = qu1.poll();
                if(!horiIndexSet.contains(p.horiIndex)){
                    horiIndexSet.add(p.horiIndex);
                    res.add(p);
                }
                if(p.node.left!=null){
                    qu1.offer(new Pair(p.node.left, p.horiIndex-1));
                }
                if(p.node.right!=null){
                    qu1.offer(new Pair(p.node.right, p.horiIndex+1));
                }

            }

            Collections.sort(res, Comparator.comparingInt(p->p.horiIndex));
            System.out.print(res);
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
