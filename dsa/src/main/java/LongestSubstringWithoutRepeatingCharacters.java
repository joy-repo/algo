import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    static String STR = "GEEKSFORGEEKS";
    //                   012345678
    public static void main(String[] args) {
        lswrc();
    }

    private static void lswrc() {

        int l =0;
        int r =0;

        Set<Character> set = new HashSet<>();

        int res =0;
        int resL=0;
        int resR=0;

        while (r < STR.length()){

            while (set.contains(STR.charAt(r))){
                set.remove(STR.charAt(l));
                l++;
            }
            set.add(STR.charAt(r));
            if(res<r-l+1){
                res=r-l+1;
                resL=l;
                resR=r;
            }
            r++;
        }

        System.out.println(res);
        System.out.println(resL);
        System.out.println(resR);


    }
}
