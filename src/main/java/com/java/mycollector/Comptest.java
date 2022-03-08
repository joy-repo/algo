package com.java.mycollector;


import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Comptest {

    public static void main(String[] args) {

        List<Dish> menu = new ArrayList<>();
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(d -> d.getCalories());
        Optional<Dish> mostCalorieDish =
                menu.stream()
                        .collect(Collectors.maxBy(dishCaloriesComparator));

        Map<String, List<String>> ssdd = menu.stream().collect(
                groupingBy(Dish::getType, mapping(Dish::getType, toList())));


        Map<String, Optional<Dish>> mm = menu.stream().collect(
                groupingBy(Dish::getType, maxBy(Comparator.comparingInt(d -> d.getCalories()))));


        Map<String, Dish> mmuu = menu.stream().collect(
                groupingBy(Dish::getType,
                            collectingAndThen(
                                maxBy(Comparator.comparingInt(d -> d.getCalories())), Optional::get)));


        Dish dd = menu.stream().collect(
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(d -> d.getCalories())), Optional::get));

        List<Temp> ll = new ArrayList<>();

        Comparator<Temp> c = Comparator.comparing(i -> 0);

        Optional<Temp> t = ll.stream().collect(Collectors.maxBy(c));
    }
}

class Dish {

    private int calories;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}

class Temp{}

