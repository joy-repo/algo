package test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {



        ProductCreationTimeDetails pd = new ProductCreationTimeDetails("a1", 677688l);
        List<ProductCreationTimeDetails> list = new ArrayList<>();
        list.add(new ProductCreationTimeDetails("a1", 123l));
        list.add(new ProductCreationTimeDetails("a2", 456l));
        list.add(new ProductCreationTimeDetails("a3", 11l));
        list.add(new ProductCreationTimeDetails("a4", 12l));

        PriorityQueue<ProductCreationTimeDetails> priorityQueue = list.stream()
                .map(e-> new ProductCreationTimeDetails(e.getProduct(), e.getDate()))
                .collect(Collectors.toCollection(PriorityQueue::new));
        StringBuilder  conditionStringBuilder = new StringBuilder();

        while (!priorityQueue.isEmpty()){
            if(conditionStringBuilder.length()>0){
                conditionStringBuilder.append("_"+ priorityQueue.poll().getProduct());
            } else {
                conditionStringBuilder.append(priorityQueue.poll().getProduct());
            }
        }
        System.out.println(conditionStringBuilder.toString());
    }
}
