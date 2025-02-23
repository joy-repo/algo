package atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*

1st Strategy -> supposed 2 candidates (A, B) have same points. We have to declare the candidate as winner whoever reaches the winning point 1st.
2nd strategy -> votes placed in the ballot. Whoever has the maxium votes in ballot at 0th index then 1st index and then last index.


For strategy 1 -> I implemented a doubly linked list having each node as set.
    Basically I was trying to implement the ALL O(1) problem's solution. With help of this I can return the 0th index on the list's node.

For strategy 2 -> I tried maintaing an array of points at each index for each candidate. EG - A: [0,0,0]
 */

public class RankTeamsByVotes {

    static String[] VOTES = {"ABC","ACB","ABC","ACB","ABC","ACB"}; //{"ABC", "ACB", "BAC", "BCA", "CAB", "CBA"};

    public static void main(String[] args) {
        getTeamsByWinners();
    }

    private static void getTeamsByWinners() {

        Map<Character, int[]> aggregatedVotes = new HashMap<>();
        char[][] storingWinningPoints = new char[VOTES[0].length()][VOTES.length]; //strategy 1

        for(int k=0; k< VOTES.length; k++){
            for(int i = 0; i<VOTES[k].length(); i++){
                aggregatedVotes.putIfAbsent(VOTES[k].charAt(i), new int[VOTES[k].length()]);
                aggregatedVotes.get(VOTES[k].charAt(i))[i]++;
                storingWinningPoints[i][k] = VOTES[k].charAt(i);

            }
        }
        List<Character> teams = new ArrayList<>(aggregatedVotes.keySet());

        teams.sort((a,b)-> {
                    for (int i = 0; i < VOTES[0].length(); i++) {
                        if (aggregatedVotes.get(a)[i] != aggregatedVotes.get(b)[i]) {
                            return aggregatedVotes.get(b)[i] - aggregatedVotes.get(a)[i];
                        }
                    }

                    for (char[] cArr : storingWinningPoints) {
                        for(char c : cArr){
                            if(c==a) return -1;
                            if(c==b) return 1;
                        }
                    }

                    return a-b;
                });


        aggregatedVotes.forEach((k,v)->{
            System.out.println();
            System.out.println(k +"::");
            for(int a : v){
                System.out.print(a +",");
            }

        });
        System.out.println();
        System.out.println(teams);
    }
}
