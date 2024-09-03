package stacks_queues.prefix_infix_postfix;

import java.util.Stack;

public class Prefix_2_Infix {

    //TODO : R<---L
    //TODO : (t1 + c + t2)

    static String PREFIX = "*+PQ-MN";

    public static void main(String[] args) {
        pre2in();
    }

    private static boolean isOperand(char c){
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static void pre2in() {

        Stack<String> stk = new Stack<>();
        char[] cArr = PREFIX.toCharArray();

        for(int i = cArr.length-1; i>=0; i--){
            if(isOperand(cArr[i])) stk.push(cArr[i]+"");
            else {
                String str = "("+stk.pop()+cArr[i]+stk.pop()+")";
                stk.push(str);
            }
        }
        System.out.println(stk.pop());
    }
}
