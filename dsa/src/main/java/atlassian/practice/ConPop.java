package atlassian.practice;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

public class ConPop {

    @Getter
    @Setter
    @AllArgsConstructor
    @ToString
    class Container{
        private int contentId;
        private int popularityScore;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Container container = (Container) o;
            return contentId == container.contentId && popularityScore == container.popularityScore;
        }

        @Override
        public int hashCode() {
            return Objects.hash(contentId, popularityScore);
        }
    }

    private Map<Integer, List<Container>> scoreToContainerListMap;
    private Map<Integer, Container> contentIdToContainer;
    private int maxScore = 0;

    public ConPop(){
        scoreToContainerListMap = new HashMap<>();
        contentIdToContainer = new HashMap<>();
    }

    public void increaseScore(int contentId){
        updateScore(contentId, 1);
    }

    public void decreaseScore(int contentId){
        updateScore(contentId, -1);
    }

    public List<Container> getMostPopular(){
        return scoreToContainerListMap.getOrDefault(maxScore, new ArrayList<>());
    }

    private void updateScore(int contentId, int deltaScore){
        Container oldContainer = contentIdToContainer.get(contentId);

        // ERROR CONDITION
        if (oldContainer == null && deltaScore < 0) return;

        if (oldContainer == null) {
            Container newContainer = new Container(contentId, deltaScore);
            contentIdToContainer.put(contentId, newContainer);
            scoreToContainerListMap.putIfAbsent(deltaScore, new ArrayList<>());
            scoreToContainerListMap.get(deltaScore).add(newContainer);
            maxScore = Math.max(maxScore, deltaScore);
            return;
        }

        // Remove old container from old score bucket
        scoreToContainerListMap.get(oldContainer.popularityScore).remove(oldContainer);
        if (scoreToContainerListMap.get(oldContainer.popularityScore).isEmpty()) {
            scoreToContainerListMap.remove(oldContainer.popularityScore);
//            if (oldContainer.popularityScore == maxScore) {
//                maxScore = scoreToContainerListMap.keySet().stream()
//                        .max(Integer::compare).orElse(0); // Recalculate maxScore
//            }
        }

        if(!scoreToContainerListMap.containsKey(oldContainer.popularityScore) && deltaScore<0 && maxScore==oldContainer.popularityScore){
            maxScore=oldContainer.popularityScore += deltaScore;
        }

        // Update container popularity
        oldContainer.popularityScore += deltaScore;
        scoreToContainerListMap.putIfAbsent(oldContainer.popularityScore, new ArrayList<>());
        scoreToContainerListMap.get(oldContainer.popularityScore).add(oldContainer);



        // Update maxScore if needed
        maxScore = Math.max(maxScore, oldContainer.popularityScore);
    }

    public static void main(String[] args) {
        ConPop tracker = new ConPop();
        tracker.increaseScore(1);
        tracker.increaseScore(2);
        tracker.increaseScore(2);
        tracker.increaseScore(2);
        tracker.increaseScore(1);
        tracker.increaseScore(1);
        tracker.decreaseScore(2);
        tracker.increaseScore(3);
        tracker.increaseScore(3);
        tracker.increaseScore(3);
        System.out.println(tracker.getMostPopular());
        System.out.print(tracker.scoreToContainerListMap);
    }
}
