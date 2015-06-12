package net.bluepoet.chap5;

public class Node<E extends Comparable<E>> implements Tree<E> {
	private E value;
	private Tree<E> left;
	private Tree<E> right;

	public Node(E value, Tree<E> left, Tree<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public Tree<E> getLeft() {
		return left;
	}

	public void setLeft(Tree<E> left) {
		this.left = left;
	}

	public Tree<E> getRight() {
		return right;
	}

	public void setRight(Tree<E> right) {
		this.right = right;
	}

	@Override
	public boolean search(E toFind) {
		if (toFind.equals(value)) {
			return true;
		}

		if (toFind.compareTo(value) < 0) {
			return left.search(toFind);
		}

		return right.search(toFind);
	}

	@Override
	public void insert(E toInsert) {
		if (toInsert.compareTo(value) < 0) {
			left.insert(toInsert);
		} else {
			right.insert(toInsert);
		}

	}

}
