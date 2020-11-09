package com.app.collection.linear.nodes;

public final class SimpleNodeLinked<T> {
    public T value;
    public SimpleNodeLinked<T> next;
    public SimpleNodeLinked(T value) {
        this.value = value;
    }
}