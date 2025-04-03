package com.my;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MMMMMM {


    public static <T> void main(String[] args) {

        List<ClusterDiffInfo<T>> clusterDiffInfoList = new ArrayList<>();

        clusterDiffInfoList.sort(Comparator
                .<ClusterDiffInfo<T>>comparingInt(a -> -(a.added().size()+a.deleted().size()))
                .thenComparing(a-> -a.deleted().size())
                .thenComparing(a-> a.added().size())
                .thenComparing(a->a.c1()));







    }
}
