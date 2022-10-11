package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptable;
import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListDataAdaptor implements DataAdaptor {

    public static ListDataAdaptor create() {
        return new ListDataAdaptor();
    }

    public static ListDataAdaptor of(DataAdaptor... values) {
        return new ListDataAdaptor(Arrays.asList(values));
    }

    public static ListDataAdaptor of(DataAdaptable... values) {
        return ofPersistent(Arrays.asList(values));
    }

    public static ListDataAdaptor of(List<DataAdaptor> list) {
        return new ListDataAdaptor(list);
    }

    public static ListDataAdaptor ofPersistent(List<DataAdaptable> list) {
        return new ListDataAdaptor(list.stream().map(DataAdaptable::write).collect(Collectors.toList()));
    }

    protected List<DataAdaptor> list;

    public ListDataAdaptor(List<DataAdaptor> list) {
        this.list = list;
    }

    public ListDataAdaptor() {
        this.list = new ArrayList<>();
    }

    @Override
    public Object asPrimitive() {
        return list.stream().map(DataAdaptor::asPrimitive).collect(Collectors.toList());
    }

    @Override
    public DataAdaptor clone() {
        return new ListDataAdaptor(new ArrayList<>(list));
    }

    @Override
    public boolean asBoolean() {
        return true;
    }

    @Override
    public byte asByte() {
        return (byte) list.size();
    }

    @Override
    public short asShort() {
        return (short) list.size();
    }

    @Override
    public int asInt() {
        return list.size();
    }

    @Override
    public long asLong() {
        return list.size();
    }

    @Override
    public float asFloat() {
        return list.size();
    }

    @Override
    public double asDouble() {
        return list.size();
    }

    @Override
    public String asString() {
        return list.toString();
    }

    @Override
    public DataAdaptor set(int index, DataAdaptor value) {
        return list.set(index, value);
    }

    @Override
    public DataAdaptor get(int index) {
        return Optional.ofNullable(list.get(index)).orElse(NullDataAdaptor.NULL);
    }

    @Override
    public DataAdaptor get(int index, @NotNull DataAdaptor defaultValue) {
        return Optional.ofNullable(list.get(index)).orElse(defaultValue);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(DataAdaptor value) {
        list.add(value);
    }

    @Override
    public Stream<DataAdaptor> stream() {
        return list.stream();
    }
}
