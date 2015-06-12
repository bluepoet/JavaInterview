package net.bluepoet.chap5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleTreeTest {
	@Test
	@SuppressWarnings("unchecked")
	public void createTree() {
		final SimpleTree<Integer> root = new SimpleTree(7, null, null);
		root.insert(3);
		root.insert(9);
		root.insert(10);

		assertTrue(root.search(10));
		assertFalse(root.search(4));
		assertEquals(Integer.valueOf(10), root.getRight().getRight().getValue());
		assertNull(root.getRight().getLeft());
	}

	@Test
	public void complexTreeTest() {
		final Node<Integer> root = new Node<Integer>(7, null, null);
		root.setLeft(new Leaf<Integer>(root));
		root.setRight(new Leaf<Integer>(root));

		root.insert(3);
		assertTrue(root.search(3));
		assertFalse(root.search(4));
		assertNotNull(root.getLeft());
	}
}

class SimpleTree<E extends Comparable<E>> {
	private E value;
	private SimpleTree<E> left;
	private SimpleTree<E> right;

	public SimpleTree(E value, SimpleTree<E> left, SimpleTree<E> right) {
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public E getValue() {
		return value;
	}

	public SimpleTree<E> getLeft() {
		return left;
	}

	public SimpleTree<E> getRight() {
		return right;
	}

	public boolean search(final E toFind) {
		if (toFind.equals(value)) {
			return true;
		}

		if (toFind.compareTo(value) < 0 && left != null) {
			return left.search(toFind);
		}

		return right != null && right.search(toFind);
	}

	@SuppressWarnings("unchecked")
	public void insert(final E toInsert) {
		if (toInsert.compareTo(value) < 0) {
			if (left == null) {
				left = new SimpleTree(toInsert, null, null);
			} else {
				left.insert(toInsert);
			}
		} else {
			if (right == null) {
				right = new SimpleTree(toInsert, null, null);
			} else {
				right.insert(toInsert);
			}
		}
	}
}
