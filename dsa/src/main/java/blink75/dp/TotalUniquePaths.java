package blink75.dp;

///https://www.codingninjas.com/studio/problems/total-unique-paths_1081470?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
///Striver DP -8

import java.util.Arrays;

public class TotalUniquePaths {

  static int COL = 4;
  static int ROW = 3;

  public static void main(String[] args) {

    System.out.println("-------RECURRSION ------");
    int res = solRecc(1, 1);
    System.out.println(res);
    System.out.println("-------DP------");

    sol_DP();

    System.out.println();

    System.out.println("-------MEMORIZATION------");

    int[][] DP = new int[ROW][COL];

    for (int[] temp : DP) {
      Arrays.fill(temp, -1);
    }

    int result = solRecc_MEMORIZATION(1, 1, DP);

    System.out.println(result);

  }

  private static int solRecc_MEMORIZATION(int r, int c, int[][] DP) {

    if (r > ROW || c > COL) {
      return 0;
    }

    if (DP[r][c] != -1)
      return DP[r][c];

    int down = solRecc(r + 1, c);
    int right = solRecc(r, c + 1);

    DP[r][c] = down + right;

    return DP[r][c];

  }

  private static void sol_DP() {
    int[][] DP = new int[ROW][COL];

    for (int[] temp : DP) {
      Arrays.fill(temp, 1);
    }

    for (int r = 0; r < ROW; r++) {

      for (int c = 0; c < COL; c++) {
        if (r > 0 && c > 0) {
          DP[r][c] = DP[r - 1][c] + DP[r][c - 1];
        } else if (r > 0) {
          DP[r][c] = DP[r - 1][c];
        } else if (c > 0) {
          DP[r][c] = DP[r][c - 1];
        }
      }
    }

    for (int i = 0; i < DP.length; i++) {
      System.out.println();
      for (int j = 0; j < DP[0].length; j++) {
        System.out.print(DP[i][j] + ",");
      }
    }
  }

  private static int solRecc(int r, int c) {

    if (r == ROW && c == COL) {
      return 1;
    }
    if (r > ROW || c > COL) {
      return 0;
    }

    return solRecc(r + 1, c) + solRecc(r, c + 1);

  }

}
