package io.weavestudio.commoneditlib.dataadaptor.impl;

import io.weavestudio.commoneditlib.dataadaptor.DataAdaptor;

public class ObjectDataAdaptor implements DataAdaptor {

    protected Object object;
    
    public ObjectDataAdaptor(Object object) {
        this.object = object;
    }

    @Override
    public Object asPrimitive() {
        return object;
    }

    @Override
    public DataAdaptor clone() {
        return new ObjectDataAdaptor(object);
    }

    @Override
    public byte asByte() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not byte");
        return ((Number) object).byteValue();
    }

    @Override
    public short asShort() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not short");
        return ((Number) object).shortValue();
    }

    @Override
    public int asInt() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not int");
        return ((Number) object).intValue();
    }

    @Override
    public long asLong() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not long");
        return ((Number) object).longValue();
    }

    @Override
    public float asFloat() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not float");
        return ((Number) object).floatValue();
    }

    @Override
    public double asDouble() {
        if (!(object instanceof Number)) throw new IllegalStateException(object.getClass().getName() + " is not double");
        return ((Number) object).doubleValue();
    }

    @Override
    public String asString() {
        return object.toString();
    }

//    public PersistentData asData() {
//        if (object instanceof PersistentData) return (PersistentData) object;
//        else if (object instanceof Map) return new MapPersistentData((Map<String, Object>) object);
//        else throw new IllegalStateException("Cannot transform to data");
//    }
//
//    public <T> T[] asTypedArray(Function<Object, T> elementProcessor) {
//        if (!(object instanceof Object[])) return null;
//        Object[] origin = (Object[]) object;
//        T[] array = (T[]) new Object[origin.length];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = elementProcessor.apply(origin[i]);
//        }
//        return array;
//    }
//
//    public <T extends PersistentValue> T[] asArray(Function<Object, T> elementProcessor) {
//        if (!(object instanceof Object[])) return null;
//        Object[] origin = (Object[]) object;
//        T[] array = (T[]) new Object[origin.length];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = elementProcessor.apply(origin[i]);
//        }
//        return array;
//    }

//    public byte[] asByteArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not byte[]");
//        return (byte[]) object;
//    }
//
//    public short[] asShortArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not short[]");
//        return (short[]) object;
//    }
//
//    public int[] asIntArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not int[]");
//        return (int[]) object;
//    }
//
//    public long[] asLongArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not long[]");
//        return (long[]) object;
//    }
//
//    public float[] asFloatArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not float[]");
//        return (float[]) object;
//    }
//
//    public double[] asDoubleArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not double[]");
//        return (double[]) object;
//    }
//
//    public String[] asStringArray() {
//        if (!(object instanceof byte[])) throw new IllegalStateException(object.getClass().getName() + " is not String[]");
//        return (String[]) object;
//    }
//
//    public PersistentData[] asDataArray() {
//        if (!(object instanceof PersistentData[])) throw new IllegalStateException(object.getClass().getName() + " is not PersistentData[]");
//        return (PersistentData[]) object;
//    }
    
}
