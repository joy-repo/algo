package recurrsion;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

public class WordSearch {

    static char[][] board = {{'A','B','C','E'},
                             {'S','F','C','S'},
                             {'A','D','E','E'}};

    static String target = "ABCCED";


    public static void main(String[] args) {
        Set<Pair> set = new HashSet<>();
        doWordSearch(set, 0, 0, 0)
    }

    private static boolean doWordSearch(Set<Pair> set, int x, int y, int targetIndex) {
       if(targetIndex==target.length()) return true;
      // if(set.size()>= board[0].length*board.length) return false;

       if()

    }


    @EqualsAndHashCode
    class Pair{
        int x;
        int y;
    }
}
