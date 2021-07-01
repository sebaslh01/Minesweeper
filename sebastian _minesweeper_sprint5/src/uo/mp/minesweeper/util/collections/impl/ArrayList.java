package uo.mp.minesweeper.util.collections.impl;




import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.collections.Collection;



public class ArrayList<E> extends AbstractList<E>   {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private static final int INITIAL_CAPACITY = 20;
	private E [] elements;
	public ArrayList() {
		this(INITIAL_CAPACITY);
	}



	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		ArgumentChecks.isTrue(capacity>=0, "Capacity has to be greater than zero");
		elements = (E[]) new Object [capacity];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList(Collection<E> other) {
		elements = (E[]) new Object[other.size()];
		for (E e : other) {
			this.add(e);
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		elements = (E[]) new Object [INITIAL_CAPACITY];
		resetNumOfElements();

	}

	@Override
	public E get(int index) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		return elements[index];
	}
	
	@Override
	public E set(int index, E element) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		E previousElement = elements[index];
		elements[index] = element;
		return previousElement;
	}

	@Override
	public void add(int index, E element) {
		ArgumentChecks.isTrue(element!=null, "The element to insert cannot be null");
		ArgumentChecks.checkBounds(index >= 0 && index<=size(), "Index out of bounds");
		if(size() >= this.elements.length) {
			this.moreMemory(); 
		}
		for(int i=size(); i>index; i--) {
			this.elements[i] = this.elements[i-1];
		}
		this.elements[index] = element;
		increaseNumOfElements();
	}

	@Override
	public E remove(int index) {
		ArgumentChecks.checkBounds(index>=0 && index<size(), "Index out of bounds");
		E removed = get(index);
		elements[index] = null;
		if(index<size()) {
			for (int i = index; i <= size(); i++) {
				elements[i] = elements[i+1];
			}
		}
		elements[size()] = null;
		decreaseNumOfElements();
		return removed;
	}


	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<E>{
		private int position;
		public ArrayListIterator() {
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
				return get(position-1);}
			else
				throw new NoSuchElementException();
		}
		
	}
	private void moreMemory() {
		@SuppressWarnings("unchecked")
		E [] aux =(E[])new Object [2 * elements.length];
		System.arraycopy(elements, 0, aux, 0, elements.length);
		elements = aux;
		
	}


}
