package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;

public class NumberDataAdaptor implements DataAdaptor {

    public static final NumberDataAdaptor ZERO = new NumberDataAdaptor(0);

    public static NumberDataAdaptor of(Number number) {
        if(number == null) return ZERO;
        else return new NumberDataAdaptor(number);
    }

    protected Number number;

    public NumberDataAdaptor(Number number) {
        this.number = number;
    }

    public Number getNumber() {
        return number;
    }

    @Override
    public Object asPrimitive() {
        return number;
    }

    @Override
    public DataAdaptor clone() {
        return new NumberDataAdaptor(number);
    }

    @Override
    public boolean asBoolean() {
        return number.intValue() != 0;
    }

    @Override
    public byte asByte() {
        return number.byteValue();
    }

    @Override
    public short asShort() {
        return number.shortValue();
    }

    @Override
    public int asInt() {
        return number.intValue();
    }

    @Override
    public long asLong() {
        return number.longValue();
    }

    @Override
    public float asFloat() {
        return number.floatValue();
    }

    @Override
    public double asDouble() {
        return number.doubleValue();
    }

    @Override
    public String asString() {
        return number.toString();
    }
}
