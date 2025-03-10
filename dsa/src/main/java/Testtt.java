import lombok.val;

import java.util.*;

/*
We want to have a voting system where we have list of candidates:

Given an array of names of candidates in an election.
A candidate's name in the array represents a vote cast on the candidate.
if candidates have equal scores, print them. Find the candidates with the highest votes.


Examples:
Input:  votes[] = ["john", "johnny", "jackie", "johnny", "john", "jackie", "jamie", "jamie", "john",
"johnny", "jamie", "johnny", "john"]
Output: john jonny
Explanation: We have four Candidates wit
h name as 'John', 'Johnny', 'jamie', 'jackie'. The candidates John and Johny get maximum votes.
Input: votes[] = ["virat", "rohit", "rishabh", "rohit", "virat", "rohit"]
Output: rohit
Explanation: We have three Candidates with name as 'virat', 'rohit', 'rishabh'. The candidate Rohit get maximum votes.


*/
class Candidates {



    public static void main(String args[]){

        String[] votes = {"john", "johnny", "jackie", "johnny", "john", "jackie", "jamie", "jamie", "john",
                "johnny", "jamie", "johnny", "john"};

        Map<String, Integer> voteMap = new HashMap<>();

        for(String str : votes){
            voteMap.put(str, voteMap.getOrDefault(str, 0)+1);
        }

        System.out.println(voteMap);

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((e1,e2)->e2.getValue()-e1.getValue());

        pq.addAll(voteMap.entrySet());
        int votesMax = pq.peek().getValue();

        while(pq.peek().getValue()==votesMax) {

            System.out.println(pq.peek().getKey() + "---" + pq.peek().getValue());
            pq.poll();
        }





        List<String> result = new ArrayList<>();
        int maxVote=0;

        for(Map.Entry<String, Integer> e : voteMap.entrySet()){
            if(e.getValue() >= maxVote){
                if(e.getValue()> maxVote) result.clear();
                result.add(e.getKey());
                maxVote=e.getValue();
            }

        }

        System.out.println(result + "-" +maxVote);



    }

}


public class Testtt {

    public static void main(String[] args) {
        IT it = new IT() {
            @Override
            public void just() {

            }
        };

        it.getTest();
    }
}

interface IT{

    public default void getTest(){
        System.out.println("tested");
    }

    public abstract void just();
}

