package stacks_queues.prefix_infix_postfix;

import java.util.Stack;

public class PostFix_2_Infix {

    static String POSTFIX = "AB-DE+F*/";

    public static void main(String[] args) {
        p2i();
    }

    private static boolean isOperand(char c){
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static void p2i() {

        Stack<String> stk = new Stack<>();

        for(char c : POSTFIX.toCharArray()){
            if(isOperand(c)){
                stk.push(c+"");
            } else {
                String t1 = stk.pop();
                String t2 = stk.pop();
                String str = "("+t2+c+t1+")";
                stk.push(str);
            }
        }
        System.out.println(stk.pop());
    }
}
