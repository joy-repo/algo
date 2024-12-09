package recurrsion;

public class RatInMaze {

    private static  int COUNT = 0;
    static int[][] maze = {
            {1, 0, 0, 0},
            {1, 1, 0, 1},
            {1, 1, 0, 0},
            {0, 1, 1, 1}
   };

    public static void main(String[] args) {
       int res =  solRatInMaze(0,0);
       System.out.println(res);
       System.out.println(COUNT);
    }

    private static int solRatInMaze(int c, int r) {
        if(c==maze.length-1 && r ==maze[0].length-1) {
            COUNT++;
            return 1;
        }
        if(c>=maze.length) return 0;
        if(r>=maze[0].length) return 0;
        int res=0;
        if(maze[c][r]==1){
            res=res+solRatInMaze(c+1, r);
            res=res+solRatInMaze(c, r+1);
            //return res;
        }
        return res;
    }
}
