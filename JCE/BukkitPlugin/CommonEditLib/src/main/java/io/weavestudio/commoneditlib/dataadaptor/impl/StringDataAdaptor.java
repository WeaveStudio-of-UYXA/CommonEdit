package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;
import io.weavestudio.commoneditlib.utils.StringUtils;

import java.util.Arrays;
import java.util.List;

public class StringDataAdaptor implements DataAdaptor {

    public static final List<String> TRUE_VALUES = Arrays.asList("true", "True", "yes", "Yes");
    public static final List<String> FALSE_VALUES = Arrays.asList("false", "False", "no", "No");

    public static final StringDataAdaptor EMPTY = new StringDataAdaptor("");

    public static StringDataAdaptor of(String string) {
        if (StringUtils.isBlank(string)) return EMPTY;
        return new StringDataAdaptor(string);
    }

    protected String string;
    protected Number number;
    protected Boolean bool;

    public StringDataAdaptor(String string) {
        this.string = string;
        if (TRUE_VALUES.contains(string)) {
            bool = true;
        } else if (FALSE_VALUES.contains(string)) {
            bool = false;
        } else {
            bool = null;
        }

        try {
            number = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            number = null;
        }
    }

    @Override
    public Object asPrimitive() {
        return string;
    }

    @Override
    public DataAdaptor clone() {
        return new StringDataAdaptor(string);
    }

    @Override
    public boolean asBoolean() {
        if (bool == null) return DataAdaptor.super.asBoolean();
        return bool;
    }

    @Override
    public byte asByte() {
        if (number == null) return DataAdaptor.super.asByte();
        return number.byteValue();
    }

    @Override
    public short asShort() {
        if (number == null) return DataAdaptor.super.asShort();
        return number.shortValue();
    }

    @Override
    public int asInt() {
        if (number == null) return DataAdaptor.super.asInt();
        return number.intValue();
    }

    @Override
    public long asLong() {
        if (number == null) return DataAdaptor.super.asLong();
        return number.longValue();
    }

    @Override
    public float asFloat() {
        if (number == null) return DataAdaptor.super.asFloat();
        return number.floatValue();
    }

    @Override
    public double asDouble() {
        if (number == null) return DataAdaptor.super.asDouble();
        return number.doubleValue();
    }

    @Override
    public String asString() {
        return string;
    }

    @Override
    public DataAdaptor get(int index) {
        return StringDataAdaptor.of(String.valueOf(string.charAt(index)));
    }

    @Override
    public int size() {
        return string.length();
    }
}
