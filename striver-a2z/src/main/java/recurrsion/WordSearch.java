package recurrsion;

import lombok.AllArgsConstructor;
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
        Boolean res = doWordSearch(set, 0, 0, 0);
        System.out.println(res);
    }

    private static boolean doWordSearch(Set<Pair> set, int x, int y, int targetIndex) {
       if(targetIndex==target.length()) {
           System.out.println(set);return true;
       }
      // if(set.size()>= board[0].length*board.length) return false;
       if(set.contains(new Pair(x,y))) return false;

       if(x<0||x>=board[0].length||y<0||y>=board.length) return false;
        if(target.charAt(targetIndex)!= board[y][x]) return false;
       if(target.charAt(targetIndex)== board[y][x]) {
           set.add(new Pair(x, y));
       }

           return doWordSearch(set, x + 1, y, targetIndex + 1)
                   || doWordSearch(set, x - 1, y, targetIndex + 1)
                   || doWordSearch(set, x, y + 1, targetIndex + 1)
                   || doWordSearch(set, x, y - 1, targetIndex + 1);

    }

    @AllArgsConstructor
    @EqualsAndHashCode
    static class Pair{
        int x;
        int y;
    }
}
