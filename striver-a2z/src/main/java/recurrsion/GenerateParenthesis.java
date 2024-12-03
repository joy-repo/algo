package recurrsion;

//https://leetcode.com/problems/generate-parentheses/description/


public class GenerateParenthesis {

    static int N = 3;

    public static void main(String[] args) {
        recSol("",0, 0);
    }

    private static void recSol(String str, int opening, int closing) {
        if(closing>opening) return;
        if(closing>=N){
            System.out.println(str);
        }

        if(opening ==N){
            recSol(str+")", opening, closing+1);
        } else if(closing==opening){
            recSol(str+"(", opening+1, closing);
        } else {
            recSol(str + "(", opening + 1, closing);
            recSol(str + ")", opening, closing + 1);
        }
    }

}

