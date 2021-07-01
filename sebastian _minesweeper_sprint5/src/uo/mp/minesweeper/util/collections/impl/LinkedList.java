package uo.mp.minesweeper.util.collections.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.minesweeper.util.ArgumentChecks;





public class LinkedList<E> extends AbstractList<E>  {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private Node head;

	
	private void addFirst(E value) {
		this.head= new Node(value, this.head);
		increaseNumOfElements();
	}
	


	private Node getNode(int i) {
		int position = 0;
		Node node = this.head;
		while(position < i)  {
			node = node.next;
			position++; 
		}
		return node;

	}

	@Override
	public void clear() {
		this.head = null;
		resetNumOfElements();

	}

	@Override
	public E get(int index) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		return getNode(index).value;
	}

	@Override
	public E set(int index, E element) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		E replaced = getNode(index).value;
		getNode(index).value= element;
		return replaced;
	}

	@Override
	public void add(int index, E element) {
		ArgumentChecks.isTrue(element!=null, "Element cannot be null");
		ArgumentChecks.checkBounds(index >= 0 && index<=size(), "Index out of bounds");
		if(index==0)
			addFirst(element);
		else{
			Node previous = getNode(index-1);
			previous.next= new Node(element, previous.next);
			increaseNumOfElements();;
		}
	
	}

	@Override
	public E remove(int index) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		if(this.isEmpty())
			return null;
		E value;
		if(index== 0) { 
			value=this.head.value;
			this.head= this.head.next;
		}
		else{
			Node previous= getNode(index-1);
			value= previous.next.value;
			previous.next= previous.next.next;
		}
		decreaseNumOfElements();;
		return value;  
	}
		

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator();
	}
	private class Node {
		E value;
		Node next;
		Node(E value, Node next){
			this.value = value;
			this.next = next;
		}
	
	}
	private class LinkedListIterator implements Iterator<E>{
		private int position;
		public LinkedListIterator() {
			position = 0;
		}
		@Override
		public boolean hasNext() {
			try {
				if(get(position)!=null);
					return true;		
			}
			catch(IndexOutOfBoundsException e) {
				return false;
			}
		}

		@Override
		public E next() {
			if(this.hasNext()) {
				this.position++;
				return get(position-1);
			}
			else
				throw new NoSuchElementException();
		}
		
	}



}
