package io.weavestudio.commoneditlib.utils;

import java.util.List;

public class Feeder<T> {

    protected List<T> list;
    protected int index = 0;

    public Feeder(List<T> list, int index) {
        this.list = list;
        this.index = index;
    }

    public Feeder(List<T> list) {
        this.list = list;
    }

    public T peek() {
        return list.get(index);
    }

    public T read() {
        return list.get(index++);
    }

    public Feeder<T> skip(int amount) {
        index += amount;
        return this;
    }

    public boolean hasNext() {
        return index < list.size();
    }

    public boolean hasMore(int amount) {
        return index + amount <= list.size();
    }

    public void checkHasMore(int amount) throws IllegalArgumentException {
        if (!hasMore(amount)) throw new IllegalArgumentException("Length not enough");
    }

    public void checkHasMore() throws IllegalArgumentException {
        checkHasMore(1);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
