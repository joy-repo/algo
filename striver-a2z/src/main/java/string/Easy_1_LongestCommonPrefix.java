package string;

public class Easy_1_LongestCommonPrefix {

    static String[] STR_ARR = { "flower","flow","flight"};

    public static void main(String[] args) {
        int res = solve();
        System.out.println(STR_ARR[0].substring(0, res));
    }

    private static int solve() {
        int i=0;
        while (true){
            if(STR_ARR[0].length()== i) return i;
            char c = STR_ARR[0].charAt(i);
            for(String str : STR_ARR){
                if(c!=str.charAt(i)) return i;
            }
            i++;
        }

    }
}
