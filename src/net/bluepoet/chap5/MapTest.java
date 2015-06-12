package net.bluepoet.chap5;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class MapTest {
	@Test
	public void overwirteKey() {
		final Map<String, String> preferences = Maps.newHashMap();
		preferences.put("like", "free");
		preferences.put("dislike", "arrest");

		assertEquals("free", preferences.get("like"));

		preferences.put("like", "programming");

		assertEquals("programming", preferences.get("like"));
	}

	@Test
	public void treeMapTraversal() {
		final Map<Integer, String> counts = Maps.newTreeMap();
		counts.put(4, "four");
		counts.put(3, "three");
		counts.put(2, "two");
		counts.put(1, "one");

		final Iterator<Integer> keys = counts.keySet().iterator();
		assertEquals(Integer.valueOf(1), keys.next());
		assertEquals(Integer.valueOf(2), keys.next());
		assertEquals(Integer.valueOf(3), keys.next());
		assertEquals(Integer.valueOf(4), keys.next());
	}

	@Test
	public void linkedHashMapTraversal() {
		final Map<Integer, String> counts = Maps.newLinkedHashMap();
		counts.put(4, "four");
		counts.put(1, "one");
		counts.put(3, "three");
		counts.put(2, "two");

		final Iterator<Integer> keys = counts.keySet().iterator();
		assertEquals(Integer.valueOf(4), keys.next());
		assertEquals(Integer.valueOf(1), keys.next());
		assertEquals(Integer.valueOf(3), keys.next());
		assertEquals(Integer.valueOf(2), keys.next());
	}

	@Test
	public void setExample() {
		final Set<String> set = Sets.newHashSet();
		set.add("hello");
		set.add("welcome");
		set.add("goodbye");
		set.add("bye");
		set.add("hello");

		assertEquals(4, set.size());
	}
}
