package dp;

import java.awt.event.MouseAdapter;

public class DP_7_NINJA_TRAINING {

    static int[][] POINTS = {
            {1,2,5},
            {3,1,1},
            {3,3,3}
    };

    public static void main(String[] args) {
        byRecc(POINTS.length-1, 0);
    }

    private static int byRecc(int idx, int lastIdx) {
        if(idx<0) return 0;

        for(int i =idx; i<POINTS[idx].length; i++){
            if()
        }
    }
}
