package com.java.mycollector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ToCountingCollector<T> implements Collector<T, List<T>, Integer>{


    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (l1,l2)-> {l1.addAll(l2); return l1;};
    }

    @Override
    public Function<List<T>, Integer> finisher() {
        return l->l.size();
    }

    @Override
    public Set<Characteristics> characteristics() {
        Set<Characteristics> set = new HashSet<>();
        set.add(Characteristics.UNORDERED);
        set.add(Characteristics.CONCURRENT);
        return set;
    }
}