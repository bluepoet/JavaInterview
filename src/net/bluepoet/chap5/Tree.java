package net.bluepoet.chap5;

public interface Tree<E extends Comparable<E>> {
	boolean search(E toFind);
	void insert(E toInsert);
}
