package com.app.collection.linear.nodes;

public final class DoublyLinkedNode<T> {
    public DoublyLinkedNode<T> last;
    public T value;
    public DoublyLinkedNode<T> next;
    public DoublyLinkedNode(T value) {
        this.value = value;
    }
}