package uo.mp.minesweeper.util.collections.impl;

import uo.mp.minesweeper.util.ArgumentChecks;
import uo.mp.minesweeper.util.collections.List;

public abstract  class AbstractList<E> implements List<E>, java.io.Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private int numberOfElements;
	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return size()==0;
	}
	@Override
	public int hashCode() {
		int hashCode = 1;
		if(this.isEmpty()) return hashCode;
		for (int i = 0; i < size(); i++) {
			hashCode = 31 * hashCode + ((get(i) == null) ? 0 : get(i).hashCode());
		}
		

		return hashCode;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof List))
			return false;
		@SuppressWarnings("unchecked")
		List<E> other = (List<E>) obj;
		if (this.size() != other.size())
			return false;
		for (int i = 0; i < other.size(); i++) {
			if(!this.get(i).equals(other.get(i)))
				return false;
		}
		return true;
	}
	
	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) >= 0;
	}
	@Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < size(); i++) {
        	if(get(i)!=null)
        		result.append(get(i));
			if(i<size()-1)
				result.append(", ");
		}
        result.append("]");
        return result.toString();   
    }
	@Override
	public int indexOf(Object o) {
		if(o == null) return -1;
		for (int i = 0; i < size(); i++) {
			if(o.equals(get(i)))
				return i;
		}
		return -1;
	}
	@Override
	public boolean remove(E o) {
		int indexOfO = indexOf(o);
		if(indexOfO<0)
			return false;	
		remove(indexOfO);
		return true;
	}
	@Override
	public boolean add(E element) {
		ArgumentChecks.isTrue(element!=null, "Element cannot be null");
		add(this.size(), element);
		return true;
	}
	
	protected void increaseNumOfElements() {
		this.numberOfElements++;
	}
	protected void decreaseNumOfElements() {
		this.numberOfElements--;
	}
	protected void resetNumOfElements() {
		this.numberOfElements = 0;
	}
	
}
