package atlassian;

import java.util.*;

class Team implements Comparable<Team> {
    private final char name;
    private final int[] rankCount;

    public Team(char name, int numRanks) {
        this.name = name;
        this.rankCount = new int[numRanks];
    }

    public char getName() {
        return name;
    }

    public void incrementRank(int position) {
        rankCount[position]++;
    }

    public int[] getRankCount() {
        return rankCount;
    }

    @Override
    public int compareTo(Team other) {
        for (int i = 0; i < rankCount.length; i++) {
            if (this.rankCount[i] != other.rankCount[i]) {
                return other.rankCount[i] - this.rankCount[i]; // Higher rank first
            }
        }
        return this.name - other.name; // Tie-breaker: lexicographical order
    }
}

class RankCalculator {
    private final Map<Character, Team> teamMap;

    public RankCalculator(String[] votes) {
        this.teamMap = new HashMap<>();
        initializeTeams(votes[0]);
        processVotes(votes);
    }

    private void initializeTeams(String firstVote) {
        for (char team : firstVote.toCharArray()) {
            teamMap.put(team, new Team(team, firstVote.length()));
        }
    }

    private void processVotes(String[] votes) {
        for (String vote : votes) {
            for (int i = 0; i < vote.length(); i++) {
                teamMap.get(vote.charAt(i)).incrementRank(i);
            }
        }
    }

    public List<Team> getSortedTeams() {
        List<Team> teams = new ArrayList<>(teamMap.values());
        Collections.sort(teams);
        return teams;
    }
}

public class RankTeamsByVoteOOD {

    public static void main(String[] args) {
        String input[] = {"ABC","ACB","ABC","ACB","ACB"};
        RankTeamsByVoteOOD rankTeamsByVoteOOD = new RankTeamsByVoteOOD();
        System.out.println(rankTeamsByVoteOOD.rankTeams(input));
    }
    public String rankTeams(String[] votes) {
        if (votes == null || votes.length == 0) return "";

        RankCalculator rankCalculator = new RankCalculator(votes);
        List<Team> sortedTeams = rankCalculator.getSortedTeams();

        StringBuilder result = new StringBuilder();
        for (Team team : sortedTeams) {
            result.append(team.getName());
        }
        return result.toString();
    }
}