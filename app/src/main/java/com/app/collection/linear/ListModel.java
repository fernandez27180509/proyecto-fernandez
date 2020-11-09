package com.app.collection.linear;

import java.util.function.Consumer;

/**
 * @author jhon
 * @version 1.0
 **/
public interface ListModel<T> {

    /** Empty **/
    void addStart(T item);

    /** Empty **/
    void addEnd(T item);

    /** Empty **/
    default void add(T item) {
        this.addEnd(item);
    }

    /** Empty **/
    void removeStart();

    /** Empty **/
    void removeEnd();

    /** Empty **/
    void remove(int position);

    /** Empyt **/
    void foreach(Consumer<? super T> action);

    /** Empty **/
    default void fill(ListModel<T> list) {
        if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				this.addEnd(list.get(i));
			}
		}
    }

    /** Empty **/
    T get(int position);

    /** Empty **/
    void clear();

    /** Empty **/
    boolean isEmpty();

    /** Empty **/
    int size();

}
