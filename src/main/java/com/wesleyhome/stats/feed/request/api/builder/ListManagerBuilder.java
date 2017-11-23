package com.wesleyhome.stats.feed.request.api.builder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListManagerBuilder<T> implements Iterable<T> {

    private List<T> list;

    public ListManagerBuilder() {
        this.list = new ArrayList<>();
    }

    public void set(List<T> list) {
        if (list != null) {
            this.list = list;
        } else {
            this.list.clear();
        }
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
