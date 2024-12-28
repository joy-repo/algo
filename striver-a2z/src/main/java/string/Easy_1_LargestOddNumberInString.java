package string;

public class Easy_1_LargestOddNumberInString {

    static String STR = "4216";

    public static void main(String[] args) {

        String res = "";

        for(int i = STR.length()-1; i>=0; i--){
            Integer n = Integer.parseInt(STR.charAt(i)+"");
            if(n%2==1){
                res = STR.substring(0,i+1);
                break;
            }

        }
        System.out.println(res);
    }
}
