package atlassian.practice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ContentPopularityDLL_practice {

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
            return contentId == container.contentId;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(contentId);
        }
    }

    private Map<Integer, List<Container>> scoreToContainerListMap;
    private Map<Integer, Container> contentIdToContainer;

    private int maxScore=0;

    public ContentPopularityDLL_practice(){
        scoreToContainerListMap=new HashMap<>();
        contentIdToContainer = new HashMap<>();
    }

    public void increaseScore(int contentId){
        updateScore(contentId, 1);
    }

    public void decreaseScore(int contentId){
        updateScore(contentId, -1);
    }

    public List<Container> getMostPopular(){
        return scoreToContainerListMap.get(maxScore);
    }

    private void updateScore(int contentId, int deltaScore){

        Container oldContainer = contentIdToContainer.get(contentId);

        //ERROR CONDITION
        if(oldContainer==null && deltaScore<0) return;

        if(oldContainer==null){
            Container newContainer = new Container(contentId, deltaScore);
            contentIdToContainer.put(contentId, newContainer);
            scoreToContainerListMap.putIfAbsent(deltaScore, new ArrayList<>());
            scoreToContainerListMap.get(deltaScore).add(newContainer);
            maxScore = Math.max(maxScore, deltaScore);
            return;
        }

        scoreToContainerListMap.get(oldContainer.popularityScore).remove(oldContainer);
        contentIdToContainer.remove(oldContainer.getContentId());
        if(scoreToContainerListMap.get(oldContainer.popularityScore).isEmpty()){
            scoreToContainerListMap.remove(oldContainer.popularityScore);
        }

        Container newContainer = new Container(contentId, oldContainer.popularityScore+deltaScore);
        contentIdToContainer.put(contentId, newContainer);
        scoreToContainerListMap.putIfAbsent(newContainer.popularityScore, new ArrayList<>());
        scoreToContainerListMap.get(newContainer.popularityScore).add(newContainer);

        if(scoreToContainerListMap.get(oldContainer.popularityScore)==null && maxScore==oldContainer.popularityScore){
            maxScore= newContainer.popularityScore;
        }



    }

    public static void main(String[] args) {
        ContentPopularityDLL_practice tracker = new ContentPopularityDLL_practice();
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
