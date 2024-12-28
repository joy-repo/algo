package recurrsion;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MColoringGraph_byGraphTraversal {

//    static int Edges[][] = {
//            {0, 1},
//            {1, 2},
//            {0, 2}
//    };


    static int Edges[][] = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0},
            {0, 2}
    };

    static Map<Integer, List<Integer>> adjList = new HashMap<>();

    public static void main(String[] args) {
        for(int[] e : Edges){

            adjList.putIfAbsent(e[0], new ArrayList<>());
            adjList.get(e[0]).add(e[1]);
            adjList.putIfAbsent(e[1], new ArrayList<>());
            adjList.get(e[1]).add(e[0]);

        }
        solvebyGraphTraversal();
    }

    private static void solvebyGraphTraversal() {
        int colors =0;
        Map<Integer,Integer> node2Color = new HashMap<>();

        for(Map.Entry<Integer, List<Integer>> e : adjList.entrySet() ){

            boolean[] colorB = new boolean[adjList.size()];
            for(Integer node : e.getValue()){
               if(node2Color.containsKey(node)) colorB[node2Color.get(node)]=true;
            }
            for(int i=0; i< adjList.size(); i++){
                if(colorB[i] == false) {
                    node2Color.put(e.getKey(), i);
                    colors= Math.max(colors, i);
                    break;
                }
            }
        }
        System.out.print("res:" +(colors+1));
        System.out.println(node2Color);
    }


//    @Data
//    @AllArgsConstructor
//    static class NodeColor{
//        private int node;
//        private  int color =-1;
//
//        public NodeColor(int node){
//            this.node=node;
//            this.color = -1;
//        }
//
//        @Override
//        public int hashCode()
//        {
//            return node;
//        }
//
//        public boolean equals(Object obj){
//            if(this == obj)
//                return true;
//
//            if(obj == null || obj.getClass()!= this.getClass())
//                return false;
//            NodeColor nn = (NodeColor) obj;
//
//            return nn.node==node;
//        }


   // }
}
