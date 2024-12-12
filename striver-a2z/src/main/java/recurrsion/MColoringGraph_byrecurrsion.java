package recurrsion;

public class MColoringGraph_byrecurrsion {

    static int Edges[][] = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0},
            {0, 2}
    };

    static int MColor = 3;
    static int Vertices = 4;

    public static void main(String[] args) {
        int[] colors = new int[Vertices+1];
        boolean res = solveByRec(colors, 0);
        System.out.print(res);
    }

    private static boolean solveByRec(int[] colors, int node) {
        if(node==Vertices+1) return true;

        for(int i=1; i<= MColor; i++) {
            if(assignColor(node, colors, i)){
                colors[node]=i;
                return solveByRec(colors,node+1);
            }

        }
        return false;

    }

    private static boolean assignColor(int node, int[] colors, int i) {

        for(int[] edge : Edges){
            if(edge[0]==node){
                if(colors[edge[1]]==i) return false;
            }
            if(edge[1]==node){
                if(colors[edge[0]]==i) return false;
            }
        }
        return true;
    }
}
