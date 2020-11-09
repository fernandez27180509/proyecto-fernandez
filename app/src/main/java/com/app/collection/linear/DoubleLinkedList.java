package com.app.collection.linear;

import com.app.collection.linear.nodes.DoublyLinkedNode;
import com.app.collection.linear.nodes.SimpleNodeLinked;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author jhon
 * @version 1.0
 **/
public class DoubleLinkedList<T> implements ListModel<T> {

    /** Empty **/
    private DoublyLinkedNode<T> firstNode;

    /** Empty **/
    private int size;

    /** Default constructor **/
    public DoubleLinkedList() {
        this.size = 0;
    }

    /** Empty **/
    @Override
    public void addStart(T item) {
        DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
        if (!this.isEmpty()) {
            DoublyLinkedNode<T> assistant = this.firstNode;
            assistant.last = newNode;
            newNode.next = assistant;
        }
        this.firstNode = newNode;
        this.size++;
    }

    /** Empty **/
    @Override
    public void addEnd(T item) {
        DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
        if (!this.isEmpty()) {
            DoublyLinkedNode<T> assistant = this.firstNode;
            while (assistant.next != null) {
                assistant = assistant.next;
            }
            assistant.next = newNode;
            newNode.last = assistant;
        } else {
            this.firstNode = newNode;
        }
        this.size++;
    }

    /** Empty **/
    @Override
    public void removeStart() {
        if (!this.isEmpty()) {
            if (this.firstNode.next != null) {
                DoublyLinkedNode<T> assistant = this.firstNode;
                assistant.next.last = null;
                this.firstNode = assistant.next;
                assistant.value = null;
                assistant.next = null;
            } else {
                this.firstNode.value = null;
                this.firstNode = null;
            }
            this.size--;
        } else {
            throw new IndexOutOfBoundsException("The list is empty.");
        }
    }

    /** Empty **/
    @Override
    public void removeEnd() {
        if (!this.isEmpty()) {
            if (this.firstNode.next != null) {
                DoublyLinkedNode<T> assistant = this.firstNode;
                while (assistant.next != null) {
                    assistant = assistant.next;
                }
                assistant.last.next = null;
                assistant.value = null;
            } else {
                this.firstNode.value = null;
                this.firstNode = null;
            }
            this.size--;
        } else {
            throw new IndexOutOfBoundsException("The list is empty.");
        }
    }

    /** Empty **/
    @Override
    public void remove(int position) {
        if (!this.isEmpty()) {
            if (position < this.size) {
                if (position == 0) {
                    this.removeStart();
                } else if (position == this.size - 1) {
                    this.removeEnd();
                } else {
                    DoublyLinkedNode<T> assistant = this.firstNode;
                    int counter = 0;
                    while (counter < position) {
                        assistant = assistant.next;
                        counter++;
                    }
                    assistant.last.next = assistant.next;
                    assistant.next.last = assistant.last;
                    assistant.next = null;
                    assistant.last = null;
                    assistant.value = null;
                    this.size--;
                }
            } else {
                throw new IndexOutOfBoundsException(position + " out of range.");
            }
        } else {
            throw new IndexOutOfBoundsException("The list is empty.");
        }
    }

    /** Empty **/
    @Override
    public T get(int position) {
        if (!this.isEmpty()) {
            if (position < this.size) {
                DoublyLinkedNode<T> assistant = this.firstNode;
                int counter = 0;
                while (counter < position) {
                    assistant = assistant.next;
                    counter++;
                }
                return assistant.value;
            } else {
                throw new IndexOutOfBoundsException(position + " out of range");
            }
        } else {
            throw new NullPointerException("The list is empty");
        }
    }

    /** Empty **/
    @Override
    public void clear() {
        if (!this.isEmpty()) {
            this.firstNode = null;
            this.size = 0;
        }
    }

    /** Empty **/
    @Override
    public boolean isEmpty() {
        return this.firstNode == null && this.size == 0;
    }

    public void foreach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        if(!this.isEmpty()) {
            DoublyLinkedNode<T> assistant = this.firstNode;
            while (assistant != null) {
                action.accept(assistant.value);
                assistant = assistant.next;
            }
        }
    }

    /** Empty **/
    @Override
    public int size() {
        return this.size;
    }

    /** Empty **/
    @Override
    public String toString() {
        if (!this.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            DoublyLinkedNode<T> assistant = this.firstNode;
            while (assistant != null) {
                builder.append(assistant.value.toString()).append(",").append(" ");
                assistant = assistant.next;
            }
            builder.append("]");
            return builder.toString().replace(", ]", "]");
        } else {
            return "[]";
        }
    }

}
