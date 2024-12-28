package string;

//https://leetcode.com/problems/remove-outermost-parentheses/

import java.util.ArrayList;
import java.util.List;

public class Easy_1_RemoveOutermostParentheses {

    public static String STR = "(()())(())(()(()))";

    public static void main(String[] args) {

        int depth = 1;
        char[] res = STR.toCharArray();

        res[0]=' ';
        for(int i=1; i<res.length; i++){
            if('('== res[i]) {
                if(depth==0) res[i]=' ';
                depth++;
            }
            else {
                depth--;
                if (depth == 0) res[i]=' ';
            }
        }

        System.out.println(new String(res));
        String resF = new String(res).replace(" ", "");

        System.out.println(resF);

    }
}
