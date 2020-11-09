package com.app.collection.linear;

import com.app.collection.linear.nodes.DoublyLinkedNode;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author jhon
 * @version 1.0
 **/
public class DoubleLinkedCircularList<T> implements ListModel<T> {

	/** Empty **/
    private DoublyLinkedNode<T> firstNode;

	/** Empty **/
    private int size;

	/** Default constructor **/
    public DoubleLinkedCircularList() {
        this.size = 0;
    }

	/** Empty **/
    @Override
    public void addStart(T item) {
    	DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
        if (!this.isEmpty()) {
        	DoublyLinkedNode<T> assistant = this.firstNode;
        	assistant.last.next = newNode;
        	newNode.last = assistant.last;
        	assistant.last = newNode;
        	newNode.next = assistant;
        	this.firstNode = newNode;
        } else {
        	this.firstNode = newNode;
        	this.firstNode.next = newNode;
        	this.firstNode.last = newNode;
        }
        this.size++;
    }

	/** Empty **/
    @Override
    public void addEnd(T item) {
    	DoublyLinkedNode<T> newNode = new DoublyLinkedNode<>(item);
    	if (!this.isEmpty()) {
    		DoublyLinkedNode<T> assistant = this.firstNode;
    		do {
    			assistant = assistant.next;
    		} while (assistant.next != this.firstNode);
    		assistant.next.last = newNode;
    		newNode.last = assistant;
    		newNode.next = assistant.next;
    		assistant.next = newNode;
		} else {
			this.firstNode = newNode;
        	this.firstNode.next = newNode;
        	this.firstNode.last = newNode;
		}
    	this.size++;
    }

	/** Empty **/
    @Override
    public void removeStart() {
        if (!this.isEmpty()) {
        	if (this.firstNode.next.equals(this.firstNode)) {
        		this.firstNode.value = null;
        		this.firstNode = null;
        	} else {
        		DoublyLinkedNode<T> assistant = this.firstNode;
        		this.firstNode = assistant.next;
        		this.removeNode(assistant);
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
        	DoublyLinkedNode<T> assistant = this.firstNode;
        	do {
        		assistant = assistant.next;
        	} while (assistant.next != this.firstNode);
        	this.removeNode(assistant);
        } else {
        	throw new IndexOutOfBoundsException("The list is empty.");
        }
    }

	/** Empty **/
    @Override
    public void remove(int position) {
    	if (!this.isEmpty()) {
        	if (position == 0) {
        		this.removeStart();
        	} else if (position == this.size - 1) {
        		this.removeEnd();
        	} else {
        		if (position < this.size) {
        			DoublyLinkedNode<T> assistant = this.firstNode;
            		int counter = 0;
            		while (counter < position) {
            			assistant = assistant.next;
            			counter++;
            		}
            		this.removeNode(assistant);
        		} else {
        			throw new IndexOutOfBoundsException(position + " out of range.");
        		}
        	}
        } else {
        	throw new IndexOutOfBoundsException("The list is empty.");
        }
    }

	/** Empty **/
    private void removeNode(DoublyLinkedNode<T> node) {
    	node.last.next = node.next;
    	node.next.last = node.last;
    	node.last = null;
    	node.value = null;
    	node.next = null;
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
        		throw new IndexOutOfBoundsException(position + " out of range.");
        	}
        } else {
        	throw new IndexOutOfBoundsException("The list is empty..");
        }
    }

	/** Empty **/
    @Override
    public void clear() {
        this.firstNode = null;
        this.size = 0;
    }

	/** Empty **/
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

	@Override
	public void foreach(Consumer<? super T> action) {
		Objects.requireNonNull(action);
		if (!this.isEmpty()) {
			DoublyLinkedNode<T> assistant = this.firstNode;
			do {
				action.accept(assistant.value);
				assistant = assistant.next;
			} while (assistant != this.firstNode);
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
            do {
                builder.append(assistant.value.toString()).append(",").append(" ");
                assistant = assistant.next;
            } while (assistant != this.firstNode);
            builder.append("]");
            return builder.toString().replace(", ]", "]");
        } else {
            return "[]";
        }
    }

}
