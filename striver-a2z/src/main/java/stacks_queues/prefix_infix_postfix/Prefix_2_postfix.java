package stacks_queues.prefix_infix_postfix;

import java.util.Stack;

public class Prefix_2_postfix {
    //TODO : R <----- L
    //TODO : t1+t2+c

    static String PREFIX = "/-AB*+DEF";

    public static void main(String[] args) {
        pre2post();
    }

    private static boolean isOperand(char c){
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static void pre2post() {

        Stack<String> stk = new Stack<>();
        char[] cArr = PREFIX.toCharArray();

        for(int i = cArr.length-1; i>=0; i--){
            if(isOperand(cArr[i]))
                stk.push(cArr[i]+"");
            else
                stk.push(stk.pop()+stk.pop()+cArr[i]);

        }
        System.out.println(stk.pop());

    }
}
