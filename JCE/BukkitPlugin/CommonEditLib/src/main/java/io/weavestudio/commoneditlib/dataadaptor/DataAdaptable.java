package io.weavestudio.commoneditlib.dataadaptor;

public interface DataAdaptable {
    DataAdaptor write();
    void read(DataAdaptor data);
}
