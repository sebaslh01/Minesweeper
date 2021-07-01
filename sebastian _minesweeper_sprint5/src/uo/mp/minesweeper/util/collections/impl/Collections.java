package uo.mp.minesweeper.util.collections.impl;


import java.util.Comparator;

import uo.mp.minesweeper.util.collections.List;




public class Collections {
	  public static <T extends Comparable<? super T>> void sort(List<T> list) {
		  List<T> sortedList = new LinkedList<T>();
		  for(T obj : list) {
			  addSorted( obj, sortedList);
		  }
		  
		  //Copiar lista ordenada a la original
		  for(int i = 0; i<sortedList.size(); i++) {
			  list.set(i, sortedList.get(i));
		  }
	        
	    }
	  private static <T extends Comparable<? super T>> void addSorted(T item, List<T> sortedList) {
		  if(sortedList.isEmpty()) {
			  sortedList.add(item);
			  return;
		  }
		  T itemList = null;
		  for (int i = 0; i < sortedList.size(); i++) {
			itemList = sortedList.get(i);
			if(itemList.compareTo(item) > 0) {
				sortedList.add(i, item);
				return;
			}
		}
		  sortedList.add(item);
	  }
	  public static <T> void sort(List<T> list, Comparator <? super T> c){
		  List<T> sortedList = new LinkedList<T>();
		  int pos;
		  for(T obj: list) {
			  pos = findPositionFor(obj, sortedList, c);
			  sortedList.add(pos,obj);
		  }
		  
		  for (int i = 0; i < sortedList.size(); i++) {
			  list.set(i, sortedList.get(i));
			
		  }
		  }
	private static <T> int findPositionFor(T obj, List<T> sortedList, Comparator<? super T> c) {
		
		if(sortedList.isEmpty()) {
			  return 0;
		  }
		  T itemList = null;
		  for (int pos = 0; pos < sortedList.size(); pos++) {
			itemList = sortedList.get(pos);
			if(c.compare(itemList, obj) > 0) {
				return pos;
			}
		}
		  return sortedList.size();
	  }
	  }
 
