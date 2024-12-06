package recurrsion;

//Problem Statement: You are given a string s, partition it in such a way
// that every substring is a palindrome. Return all such palindromic partitions of s.

import com.itextpdf.text.pdf.StringUtils;

import java.util.List;

public class PalindromePartitioning {


    private static String STR = "aabb";
    public static void main(String[] args) {
       printAllPartitions(0, "");
    }

    private static void printAllPartitions(int index, String res) {
        if(index==STR.length()){
            System.out.println(res);
            return;
        }

        for(int i = index; i<STR.length(); i++){
            if(isPalindrome(index, i)){
                String tempRes = res.length()==0?STR.substring(index, i+1):res+","+STR.substring(index, i+1);
                printAllPartitions(i+1,tempRes);
            }
        }
    }

    private static boolean isPalindrome(int index, int i) {

        String str = STR.substring(index, i+1);
        String revStr = new StringBuilder(str).reverse().toString();
        return str.equals(revStr);
    }
}
