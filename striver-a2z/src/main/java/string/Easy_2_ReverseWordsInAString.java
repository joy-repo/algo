package string;

public class Easy_2_ReverseWordsInAString {

    public static String STR = "this is an amazing program";

    //EXPECTED : "program amazing an is this"
    // "! this is an amazing program"
    // "  program! this is an amazing"

    public static void main(String[] args) {
        String res = STR;
        int i=res.length()-1;
        int originalLen = i;
        int lastLen = res.length();

        while (i>=0){
            if(res.charAt(i)==' ') {
                String temp = res.substring(i,lastLen);
                res = res+temp;
                lastLen = i;
            }
            i--;
        }

        String temp = res.substring(0,lastLen);
        res = res+" "+temp;
        System.out.println(res);
        String result = res.substring(originalLen+2);
        System.out.println(result);
    }
}
