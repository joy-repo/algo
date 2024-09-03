package stacks_queues.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BalancedPrantheses {

    static String STRING="()[{}()]";

    public static void main(String[] args) {

        List<Integer> ll =new ArrayList<>();

        System.out.println(solve());
    }

    private static boolean solve() {
        Stack<Character> stack = new Stack<>();

        for(char c : STRING.toCharArray()){
            if(c=='(' || c=='{' || c=='[' ){
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                char opening = stack.pop();
                if(opening=='(' && c !=')') return false;
                if(opening=='[' && c !=']') return false;
                if(opening=='{' && c !='}') return false;

            }
        }
        return true;

    }
}
