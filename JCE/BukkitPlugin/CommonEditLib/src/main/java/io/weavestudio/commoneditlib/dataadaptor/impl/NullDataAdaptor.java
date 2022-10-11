package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;

public class NullDataAdaptor implements DataAdaptor {

    public static final NullDataAdaptor NULL = new NullDataAdaptor();

    protected NullDataAdaptor() { }

    @Override
    public Object asPrimitive() {
        return null;
    }

    @Override
    public DataAdaptor clone() {
        return NULL;
    }

    @Override
    public boolean asBoolean() {
        return false;
    }

    @Override
    public byte asByte() {
        return 0;
    }

    @Override
    public short asShort() {
        return 0;
    }

    @Override
    public int asInt() {
        return 0;
    }

    @Override
    public long asLong() {
        return 0;
    }

    @Override
    public float asFloat() {
        return 0;
    }

    @Override
    public double asDouble() {
        return 0;
    }

    @Override
    public String asString() {
        return "";
    }
}
