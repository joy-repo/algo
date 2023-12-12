package com.graph;

import java.util.Arrays;

public class GraphColoring {

    static int[][] GRAPH = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0},
    };

    public static void main(String[] args) {

        int[] color = new int[GRAPH.length];
        Arrays.fill(color, -1);
        color[0] = 1;

        minimumColorsRequired(color, 0);

        System.out.println(Arrays.toString(color));

    }

    private static void minimumColorsRequired(int[] color, int vertex) {

        if (allVerticesColored(color))
            return;


        int val = checkPossibleColor(color, vertex);
        color[vertex] = val;
        minimumColorsRequired(color, vertex + 1);
    }

    private static int checkPossibleColor(int[] color, int vertex) {

        int[] adjVertices = GRAPH[vertex];
        int c = 1;
        boolean continueOn = true;
        while (continueOn) {
            continueOn = false;
            for (int n = 0; n < adjVertices.length; n++) {
                if (adjVertices[n] > 0 && color[n] == c) {
                    continueOn = true;
                    c++;
                    break;
                }
            }
        }
        return c;
    }

    private static boolean allVerticesColored(int[] color) {
        for (int i : color)
            if (i == -1) return false;

        return true;
    }

}
