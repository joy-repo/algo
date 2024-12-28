package recurrsion;

public class Sudoku {

    static int[][] board= {
            {9, 5, 7, -1, 1, 3, -1, 8, 4},
            { 4 ,  8 ,  3 ,  -1 ,  5 ,  7 ,  1 ,  -1 ,  6 },
            { -1 ,  1 ,  2 ,  -1 ,  4 ,  9 ,  5 ,  3 ,  7 },
            { 1 ,  7 ,  -1 ,  3 ,  -1 ,  4 ,  9 ,  -1 ,  2 },
            { 5 ,  -1 ,  4 ,  9 ,  7 ,  -1 ,  3 ,  6 ,  -1},
            { 3 ,  -1 ,  9 ,  5 ,  -1 ,  8 ,  7 ,  -1 ,  1 },
            { 8 ,  4 ,  5 ,  7 ,  9 ,  -1 ,  6 ,  1 ,  3 },
            { -1 ,  9 ,  1 ,  -1 ,  3 ,  6 ,  -1 ,  7 ,  5 },
            { 7 ,  -1 ,  6 ,  1 ,  8 ,  5 ,  4 ,  -1 ,  9 }
    };

   /* static int[][] board= {
            {9, 5, 7, -1, 1, 3, -1, 8, 4},
            {4, 8, 3, -1, 5, 7, 1, -1, 6},
            {-1, 1, 2, -1, 4, 9, 5, 3, 7},
            {1, 7, -1, 3, -1, 4, 9, -1, 2},
            {5, -1, 4, 9, 7, -1, 3, 6, -1},
            {3, -1, 9, 5, -1, 8, 7, -1, 1},
            {8, 4, 5, 7, 9, -1, 6, 1, 3},
            {-1, 9, 1, -1, 3, 6, -1, 7, 5},
            {7, -1, 6, 1, 8, 5, 4, -1, 9}
    };
*/

    public static void main(String[] args) {
        System.out.println(solveSudoku(board));
        for(int j=0; j<9; j++){
            System.out.println();
            for (int i=0; i<9; i++){
                System.out.print(board[j][i]+",");
            }
        }
    }


    public static boolean solveSudoku(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == -1) {

                    for (int c = 1; c <= 9; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;

                            if (solveSudoku(board))
                                return true;
                            else
                                board[i][j] = -1;
                        }
                    }

                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[][] board, int row, int col, int c) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == c)
                return false;

            if (board[row][i] == c)
                return false;

//            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
//                return false;
        }

        int y_start = (col/3)*3;
        int x_start = (row/3)*3;
        for(int j=y_start; j<y_start+3; j++){
            for(int i=x_start; i<x_start+3; i++){
                if(board[j][i]==c) return false;
            }
        }
        return true;
    }


//    private static boolean solveSudoku() {
//
//        for(int x=0; x < 9; x++){
//            for (int y=0; y<9; y++){
//                if(board[y][x] == -1){
//                    for(int val=1; val <= 9; val++){
//                        if(isValid(y,x,val)){
//                            board[y][x] = val;
//                            if(solveSudoku())
//                                return true;
//                            else
//                                board[y][x] = -1;
//                        }
//                    }
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean isValid(int col, int row, int c) {
//        for (int i = 0; i < 9; i++) {
//            if (board[i][col] == c)
//                return false;
//
//            if (board[row][i] == c)
//                return false;
//
//            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
//                return false;
//        }
//        return true;
//    }

    private static boolean isPossible(int y, int x, int val) {

        for(int i =0; i<9; i++){
            if(board[y][i]==val || board[i][x]==val)
                return false;
        }
        int y_start = (y/3)*3;
        int x_start = (x/3)*3;
        for(int j=y_start; j<y_start+3; j++){
            for(int i=x_start; i<x_start+3; i++){
                if(board[j][i]==val) return false;
            }
        }
        return true;
    }
}
