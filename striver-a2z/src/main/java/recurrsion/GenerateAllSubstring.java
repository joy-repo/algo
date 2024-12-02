package recurrsion;

///https://www.geeksforgeeks.org/generate-binary-strings-without-consecutive-1s/?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=generate-binary-strings-without-consecutive-1s
///

public class GenerateAllSubstring {

    static String STR = "abcd";

    public static void main(String[] args) {
        System.out.println("///RECURRSION");
        solRecc("",0); ///RECURRSION
        System.out.println("///POWERSET");
        sol(); //POWERSET

    }

    private static void sol() {

        for(int i=0; i<(1<<STR.length()); i++) {
            String sub = "";
            for (int j = 0; j < STR.length(); j++) {
                if ((i & (1 << j)) != 0) {
                    sub = sub + STR.charAt(j);
                }
            }
            System.out.println(sub);
        }

    }

    private static void solRecc(String str, int len) {
        if(len == STR.length()){
            System.out.println(str);
            return;
        }
        solRecc(str+STR.charAt(len), len+1);
        solRecc(str, len+1);
    }
}
