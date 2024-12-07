package recurrsion;

public class NQueen {

    static char[][] BOARD = new char[][]{
            {'X', 'X','X','X', 'X', 'X'},
            {'X', 'X','X','X', 'X', 'X'},
            {'X', 'X','X','X', 'X', 'X'},
            {'X', 'X','X','X', 'X', 'X'},
            {'X', 'X','X','X', 'X', 'X'},
            {'X', 'X','X','X', 'X', 'X'}
    };

    public static void main(String[] args) {
        boolean res = solNQuuen(0,0);
        for(int c=0; c<BOARD.length; c++){
            System.out.println();
            for (int r =0 ; r <BOARD[0].length; r++){
                System.out.print(BOARD[c][r]);
            }
        }
    }

    private static boolean solNQuuen(int qPlaced, int row) {
        if(qPlaced == BOARD.length) return true;
        if(row>=BOARD.length) return false;

        for(int c =0; c < BOARD.length; c++){
            if(!checkCanBeAttacked(c, row)){
                BOARD[c][row] = 'Q';
                if(solNQuuen(qPlaced+1, row+1)) return true;
                BOARD[c][row] = 'X';
            }
        }
        return false;

    }

    private static boolean checkCanBeAttacked(int col, int row) {
        for(int c =0; c<BOARD.length; c++){
            if(BOARD[c][row]=='Q') return true;
        }

        for(int r =0; r<BOARD.length; r++){
            if(BOARD[col][r]=='Q') return true;
        }

        int c= col+1;
        int r = row+1;

        while (c<BOARD.length && r <BOARD[0].length){
            if(BOARD[c][r]=='Q') return true;
            c++;
            r++;
        }

        c= col-1;
        r = row-1;

        while (c>=0 && r>=0){
            if(BOARD[c][r]=='Q') return true;
            c--;
            r--;
        }

        c= col-1;
        r = row+1;

        while (c>=0 && r <BOARD[0].length){
            if(BOARD[c][r]=='Q') return true;
            c--;
            r++;
        }

        c= col+1;
        r = row-1;

        while (c<BOARD.length && r>=0){
            if(BOARD[c][r]=='Q') return true;
            c++;
            r--;
        }
        return false;

    }
}
