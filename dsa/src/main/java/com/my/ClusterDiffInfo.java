package com.my;

import java.util.Set;

public record ClusterDiffInfo<T>(int c1, int c2, Set<T> deleted, Set<T> added) {



}
