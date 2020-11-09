package com.app.model;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author jhon
 * @version 1.0
 **/
public interface DataLoader<T> extends Closeable {

    /** Method for load resource */
    void load() throws IOException;

    /** Method for get a resource */
    T get(T param);

    void close() throws IOException;

}
