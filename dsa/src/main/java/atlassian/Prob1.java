package atlassian;

public class Prob1 {

    public static void main(String[] args) {
        String s = " 3+5 / 2 ";

        char[] cArr = s.replace(" ", "").toCharArray();
        System.out.println(cArr);

    }
}
