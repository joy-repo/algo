package stacks_queues.prefix_infix_postfix;

import java.util.Stack;

public class Postfix_2_Prefix {
    //TODO : R -> L
    //TODO : c+t2+t1

    static String POSTFIX = "AB-DE+F*/";

    public static void main(String[] args) {
        post2pre();
    }
    private static boolean isOperand(char c){
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static void post2pre() {
        Stack<String> stk = new Stack<>();

        for (char c : POSTFIX.toCharArray()){
            if(isOperand(c)) stk.push(c+"");
            else {
                String t1 = stk.pop();
                String t2 = stk.pop();
                String str = c+t2+t1;
                stk.push(str);
            }
        }
        System.out.println(stk.pop());
    }
}
