package stacks_queues.learning;

import java.util.Stack;

public class BalancedPrantheses {

    static String STRING="()[{}()]";

    public static void main(String[] args) {
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
