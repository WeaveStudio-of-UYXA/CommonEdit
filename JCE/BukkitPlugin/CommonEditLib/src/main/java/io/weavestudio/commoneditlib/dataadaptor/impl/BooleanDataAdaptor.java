package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;

public class BooleanDataAdaptor implements DataAdaptor {

    public static final BooleanDataAdaptor TRUE = new BooleanDataAdaptor(true);
    public static final BooleanDataAdaptor FALSE = new BooleanDataAdaptor(false);

    public static BooleanDataAdaptor of(boolean bool) {
        return bool ? TRUE : FALSE;
    }

    protected Boolean bool;

    protected BooleanDataAdaptor(boolean bool) {
        this.bool = bool;
    }

    @Override
    public Object asPrimitive() {
        return bool;
    }

    @Override
    public DataAdaptor clone() {
        return of(bool);
    }

    @Override
    public boolean asBoolean() {
        return bool;
    }

    @Override
    public byte asByte() {
        return (byte) (bool ? 1 : 0);
    }

    @Override
    public short asShort() {
        return (short) (bool ? 1 : 0);
    }

    @Override
    public int asInt() {
        return bool ? 1 : 0;
    }

    @Override
    public long asLong() {
        return bool ? 1 : 0;
    }

    @Override
    public float asFloat() {
        return bool ? 1 : 0;
    }

    @Override
    public double asDouble() {
        return bool ? 1 : 0;
    }

    @Override
    public String asString() {
        return bool.toString();
    }
}
