package stacks_queues.prefix_infix_postfix;

import java.util.Stack;

public class Infix_2_Prefix {

   // static String INFIX_STR = "a+b*(c^d-e)";
    static String INFIX_STR = "(a+b)*c-d+f";

    public static void main(String[] args) {
        infix_2_prefix();
    }

    private static boolean isOperand(char c){
        return Character.isAlphabetic(c) || Character.isDigit(c);
    }

    private static int precedence(char c){

        if(c=='^') return 3;
        if(c=='*' || c=='/') return 2;
        if(c=='+' || c=='-') return 1;
        return -1;

    }

    private static void infix_2_prefix() {

        // 1 . reverse except (
        StringBuilder sb = new StringBuilder(INFIX_STR);
        sb.reverse();
       String res = "";
        for( char c : INFIX_STR.toCharArray()){
            if(c=='(')
                res = ')' +res;
            else if (c==')') {
                res = '(' +res;
            } else {
                res = c+ res;
            }
        }

        String ans = "";
        Stack<Character> stk = new Stack<>();

        for(char c : res.toCharArray()){
            if(isOperand(c)){
                ans = ans+ c;
            } else if (c=='(') {
                stk.push(c);
            } else if (c == ')'){
                while (!stk.isEmpty() && stk.peek()!='('){
                    ans = ans + stk.pop();
                }
                stk.pop();
            } else{
                while (!stk.isEmpty() && precedence(stk.peek())>precedence(c)){
                    ans = ans + stk.pop();
                }
                stk.push(c);
            }
        }

        while (!stk.isEmpty()){
            ans = ans + stk.pop();
        }

        System.out.println(ans);

        System.out.println(new StringBuilder(ans).reverse());

    }


}
