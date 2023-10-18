import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {



    // Function to check if the given string
    // S is IPv4 or not
    public static boolean checkIPv4(String s)
    {
        // Store the count of occurrence of '.' in the given
        // string
        int cnt = 0;
        // Traverse the string s
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                cnt++;
            }
        }
        // Not a valid IP address
        if (cnt != 3) {
            return false;
        }
        // Stores the tokens
        ArrayList<String> tokens = new ArrayList<String>();
        // StringTokenizer class check1
        StringTokenizer check1
                = new StringTokenizer(s, ".");
        // Tokenizing w.r.t. '.'
        while (check1.hasMoreTokens()) {
            tokens.add(check1.nextToken());
        }
        if (tokens.size() != 4) {
            return false;
        }
        // Check if all the tokenized strings lies in the
        // range [0, 255]
        for (int i = 0; i < tokens.size(); i++) {
            int num = 0;
            // Base Case
            if (tokens.get(i).equals("0")) {
                continue;
            }
            if (tokens.get(i).length() == 0) {
                return false;
            }
            for (int j = 0; j < tokens.get(i).length();
                 j++) {
                if (tokens.get(i).charAt(j) > '9'
                        || tokens.get(i).charAt(j) < '0') {
                    return false;
                }
                num *= 10;
                num += tokens.get(i).charAt(j) - '0';
                if (num == 0) {
                    return false;
                }
            }
            // Range check for num
            if (num > 255 || num < 0) {
                return false;
            }
        }
        return true;
    }

    // Function to check if the string
    // represents a hexadecimal number
    public static boolean checkHex(String s)
    {
        // Size of string s
        int n = s.length();
        // Iterate over string
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // Check if the character is invalid
            if ((ch < '0' || ch > '9')
                    && (ch < 'A' || ch > 'F')
                    && (ch < 'a' || ch > 'f')) {
                return false;
            }
        }
        return true;
    }


    public static boolean checkip6(String s){

        String[] strArr = s.split("::");
        List<String> resStr = new ArrayList<>();

        for(String ss : strArr){
            StringTokenizer check
                    = new StringTokenizer(s, ":");
            while (check.hasMoreTokens()){
                resStr.add(check.nextToken());
            }
        }

        if(resStr.size() >=8) return false;

        for (int i = 0; i < resStr.size(); i++) {
            int len = resStr.get(i).length();
            if (!checkHex(resStr.get(i)) || len > 4
                    || len < 1)
                return false;
        }
        return true;

    }

    // Function to check if the given
    // string S is IPv6 or not
    public static boolean checkIPv6(String s)
    {
        // Store the count of occurrence
        // of ':' in the given string
        int cnt = 0;



        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ':') {
                cnt++;
            }
        }

        // Not a valid IP Address
        if (cnt != 7) {
            return false;
        }

        // Stores the tokens
        List<String> tokens = new ArrayList<String>();

        // StringTokenizer class check1
        StringTokenizer check1
                = new StringTokenizer(s, ":");
        // Tokenizing w.r.t. ':'
        while (check1.hasMoreTokens()) {
            tokens.add(check1.nextToken());
        }

        if (tokens.size() != 8) {
            return false;
        }

        // Check if all the tokenized strings are in
        // hexadecimal format
        for (int i = 0; i < tokens.size(); i++) {
            int len = tokens.get(i).length();
            if (!checkHex(tokens.get(i)) || len > 4
                    || len < 1)
                return false;
        }
        return true;
    }
    // Function to check if the string
    // S is IPv4 or IPv6 or Invalid
    public static void checkIPAddress(String s)
    {
        // Check if the string is IPv4
        if (checkIPv4(s))
            System.out.println("IPv4");

            // Check if the string is IPv6
        else if (checkIPv6(s)) {

            System.out.println("IPv6");
        } else if (checkip6(s)) {
            System.out.println("IPv6");

        }

        // Otherwise, print "Invalid"
        else
            System.out.println("Invalid");
    }
    public static void main(String args[])
    {
        String s = "2001:db8:0:0:0:0ff0:42:8329";
        checkIPAddress(s);
    }
}
