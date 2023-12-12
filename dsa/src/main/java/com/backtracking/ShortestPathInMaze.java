package com.backtracking;
//Find the shortest path in a maze


import java.util.ArrayList;
import java.util.List;

public class ShortestPathInMaze {

    static int mat[][] =
            {
                    {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                    {0, 1, 1, 1, 1, 1, 0, 1, 0, 1},
                    {0, 0, 1, 0, 1, 1, 1, 0, 0, 1},
                    {1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                    {0, 0, 0, 1, 0, 0, 0, 1, 0, 1},
                    {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                    {0, 0, 0, 0, 1, 0, 0, 1, 0, 1},
                    {0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
                    {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
                    {0, 0, 1, 0, 0, 1, 1, 0, 0, 1},
            };

    static int Start_X = 0;
    static int Start_Y = 0;
    static int END_X = 5;
    static int END_Y = 7;

    static int xpos[]={0,0,1,-1};
    static int ypos[]={1,-1,0,0};

    static boolean[][] isVisited= new boolean[mat.length][mat[0].length];

    static int RR =Integer.MAX_VALUE;

    static List<Integer> rr = new ArrayList<>();

    public static void main(String[] args) {
        int result = solMinPath(Start_X,Start_Y,0);
        System.out.println(result);
        result = solCountPaths(Start_X,Start_Y);
        System.out.println(result);

    }

    private static int solCountPaths(int x, int y) {

        if (x == END_X && y == END_Y) {
           // RR=Math.min(RR,t_res);
            return 1;
        }
        int tt =0;

        for (int i = 0; i < xpos.length; i++) {
            int new_x = x + xpos[i];
            int new_y = y + ypos[i];

            if (isSafe(new_x, new_y) && !isVisited[new_x][new_y] && mat[new_x][new_y] == 1) {
                isVisited[new_x][new_y] = true;
                tt +=  solCountPaths(new_x, new_y);
                isVisited[new_x][new_y] = false;
            }


        }
        return tt;

    }

    private static int solMinPath(int x, int y, int t_res) {

        if (x == END_X && y == END_Y) {
            RR=Math.min(RR,t_res);
            return t_res;
        }
        int tt = Integer.MAX_VALUE;

        for (int i = 0; i < xpos.length; i++) {
            int new_x = x + xpos[i];
            int new_y = y + ypos[i];

            if (isSafe(new_x, new_y) && !isVisited[new_x][new_y] && mat[new_x][new_y] == 1) {
                isVisited[new_x][new_y] = true;
                tt = Math.min(tt, solMinPath(new_x, new_y,  t_res + 1));
                isVisited[new_x][new_y] = false;
            }


        }
        return tt;


    }

    private static boolean isSafe(int new_x, int new_y) {

        return new_x>=0 && new_x<mat[0].length && new_y>=0 && new_y<mat.length;

    }

}
