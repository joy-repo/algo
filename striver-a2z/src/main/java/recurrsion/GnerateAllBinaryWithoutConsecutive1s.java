package recurrsion;

//https://www.geeksforgeeks.org/generate-binary-strings-without-consecutive-1s/?utm_source=youtube&utm_medium=collab_striver_ytdescription&utm_campaign=generate-binary-strings-without-consecutive-1s

public class GnerateAllBinaryWithoutConsecutive1s {

    static int N = 4;
    public static void main(String[] args) {
        reccurSol("");
    }

    private static void reccurSol(String s) {
        if(s.length()==N){
            System.out.println(s);
            return;
        }

        if(s.length()> 0 && s.charAt(s.length()-1)=='1'){
            reccurSol(s+"0");
        } else {
            reccurSol(s+"0");
            reccurSol(s+"1");
        }
    }
}
