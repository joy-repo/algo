package blink75;

public class LCS {

    static String S1 = "AGGTAB";
    static String S2 = "GXTXAYB";

    public static void main(String[] args) {
        int res =solRecc(0,0);
        System.out.println(res);
        System.out.println("#####  DP #####");
        solDP();

    }

    private static void solDP() {

        int DP[][] = new int[S1.length()+1][S2.length()+1];



        for(int y=1; y<DP.length; y++){
            for (int x=1; x<DP[y].length; x++){
                if(S1.charAt(y-1)==S2.charAt(x-1)){
                    DP[y][x]=DP[y-1][x-1]+1;
                } else {
                    DP[y][x]=Math.max(DP[y-1][x], DP[y][x-1]);
                }

            }
        }

        for(int y=0; y<DP.length; y++){
            for(int x=0; x<DP[y].length; x++){
                System.out.print(DP[y][x] +"  ");
            }
            System.out.println();
        }

      //  Arrays.stream(DP).map(arr -> Arrays.asList(arr)).forEach(System.out::println);


    }

    private static int solRecc(int n1, int n2) {

        if(n1>S1.length()-1 || n2 > S2.length()-1) return 0;

       // int res =0;
        if(S1.charAt(n1)==S2.charAt(n2))
            return  1+ solRecc(n1+1, n2+1);
        else
            return Math.max(solRecc(n1+1, n2), solRecc(n1, n2+1));

    }
}
