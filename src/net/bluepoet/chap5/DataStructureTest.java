package net.bluepoet.chap5;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class DataStructureTest {
	@Test
	public void arrayCopy() {
		int[] integers = { 0, 1, 2, 3, 4 };

		int[] newIntegersArray = new int[integers.length + 1];
		System.arraycopy(integers, 0, newIntegersArray, 0, integers.length);
		integers = newIntegersArray;
		integers[5] = 5;

		assertEquals(5, integers[5]);
	}

	@Test
	public void queueInsertion() {
		final Queue<String> queue = new LinkedList<String>();
		queue.add("first");
		queue.add("second");
		queue.add("four");
		queue.add("third");

		assertEquals("first", queue.remove());
		assertEquals("second", queue.remove());
		assertEquals("four", queue.peek());
		assertEquals("four", queue.remove());
	}
}
