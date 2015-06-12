package net.bluepoet.chap5;

public class Leaf<E extends Comparable<E>> implements Tree<E> {
	private final Node<E> parent;

	public Leaf(Node<E> parent) {
		this.parent = parent;
	}

	@Override
	public boolean search(E toFind) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void insert(E toInsert) {
		if (toInsert.compareTo(parent.getValue()) < 0) {
			parent.setLeft(new Node(toInsert, new Leaf<E>(parent), new Leaf<E>(parent)));
		} else {
			parent.setRight(new Node(toInsert, new Leaf<E>(parent), new Leaf<E>(parent)));
		}
	}
}
