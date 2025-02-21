package atlassian;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankTeamsByVotes {

    static String[] VOTES = {"ABC","ACB","ABC","ACB","ACB"}; //{"ABC", "ACB", "BAC", "BCA", "CAB", "CBA"};



    public static void main(String[] args) {
        getTeamsByWinners();
    }

    private static void getTeamsByWinners() {

        Map<Character, int[]> aggregatedVotes = new HashMap<>();

        for(String vote : VOTES){
            for(int i = 0; i<vote.length(); i++){
                aggregatedVotes.putIfAbsent(vote.charAt(i), new int[vote.length()]);
                 aggregatedVotes.get(vote.charAt(i))[i]++;
            }
        }
        List<Character> teams = new ArrayList<>(aggregatedVotes.keySet());

        teams.sort((a,b)->{
            for(int i=0; i<VOTES[0].length(); i++){
                if(aggregatedVotes.get(a)[i]!=aggregatedVotes.get(b)[i]){
                    return aggregatedVotes.get(b)[i]-aggregatedVotes.get(a)[i];
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
