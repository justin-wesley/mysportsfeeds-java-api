package com.wesleyhome.stats.feed.request.api.builder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListManagerBuilder<T> implements Iterable<T> {

    private Set<T> list;

    public ListManagerBuilder() {
        this.list = new HashSet<>();
    }

    public void add(T val1, T... additionalVals) {
        if (val1 != null) {
            this.list.add(val1);
        }
        if (additionalVals != null && additionalVals.length > 0) {
            T _val1 = additionalVals[0];
            T[] _addVals = Arrays.copyOfRange(additionalVals, 1, additionalVals.length);
            add(_val1, _addVals);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
