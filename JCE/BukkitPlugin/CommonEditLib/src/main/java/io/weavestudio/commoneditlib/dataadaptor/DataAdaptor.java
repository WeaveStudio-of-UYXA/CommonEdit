package io.weavestudio.commoneditlib.dataadaptor;

import io.weavestudio.commoneditlib.dataadaptor.impl.NullDataAdaptor;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public interface DataAdaptor {

    Object asPrimitive();
    DataAdaptor clone();

    default boolean asBoolean() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default byte asByte() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default short asShort() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default int asInt() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default long asLong() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default float asFloat() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default double asDouble() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default String asString() { throw new IllegalStateException("Failed converting to PersistentData"); }
    default <T> T as() { throw new IllegalStateException("Failed converting to PersistentData"); }

//    <T> T[] asTypedArray(Function<Object, T> elementProcessor);
//    <T extends PersistentValue> T[] asArray(Function<Object, T> elementProcessor);

//    default boolean[] asBooleanArray() { throw new IllegalStateException("Failed converting to boolean array"); }
//    default byte[] asByteArray() { throw new IllegalStateException("Failed converting to byte array"); }
//    default short[] asShortArray() { throw new IllegalStateException("Failed converting to short array"); }
//    default int[] asIntArray() { throw new IllegalStateException("Failed converting to int array"); }
//    default long[] asLongArray() { throw new IllegalStateException("Failed converting to long array"); }
//    default float[] asFloatArray() { throw new IllegalStateException("Failed converting to float array"); }
//    default double[] asDoubleArray() { throw new IllegalStateException("Failed converting to double array"); }
//    default String[] asStringArray() { throw new IllegalStateException("Failed converting to String array"); }

    default DataAdaptor set(String name, DataAdaptor value) { throw new IllegalStateException("Failed setting " + name + " = " + value); }
    default DataAdaptor get(String name, @NotNull DataAdaptor defaultValue) {
        try {
            DataAdaptor ret = get(name);
            return (ret == null || ret == NullDataAdaptor.NULL) ? defaultValue : ret;
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    default DataAdaptor get(String name) { throw new IllegalStateException("Failed getting " + name); }
    default List<String> keys() { throw new IllegalStateException("Failed getting keys"); }

    default DataAdaptor set(int index, DataAdaptor value) { throw new IllegalStateException("Failed setting [" + index + "] = " + value); }
    default DataAdaptor get(int index, @NotNull DataAdaptor defaultValue) {
        try {
            DataAdaptor ret = get(index);
            return (ret == null || ret == NullDataAdaptor.NULL) ? defaultValue : ret;
        } catch (IllegalStateException e) {
            return defaultValue;
        }
    }
    default DataAdaptor get(int index) { throw new IllegalStateException("Failed getting " + index); }
    default int size() { throw new IllegalStateException("Failed getting size"); }
    default void add(DataAdaptor value) { throw new IllegalStateException("Failed adding " + value); }

    default Stream<DataAdaptor> stream() { throw new IllegalStateException("Failed converting to create Stream"); }

}
