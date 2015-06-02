package net.bluepoet.chap4;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;

public class SortTest {
	@Test
	public void sortInts() {
		final int[] numbers = { -3, -5, 1, 7, 4, 2 };
		final int[] expected = { -5, -3, 1, 2, 4, 7 };

		Arrays.sort(numbers);
		assertArrayEquals(expected, numbers);
	}

	@Test
	public void sortObjects() {
		final String[] strings = { "z", "x", "y", "abc", "zzz", "zazzy" };
		final String[] expected = { "abc", "x", "y", "z", "zazzy", "zzz" };

		Arrays.sort(strings);
		assertArrayEquals(expected, strings);
	}

	@Test(expected = ClassCastException.class)
	public void sortNotComparable() {
		final List<Person> objects = Lists.newArrayList();
		for (int i = 0; i < 10; i++) {
			objects.add(new Person(i));
		}

		Arrays.sort(objects.toArray());
	}

	@Test
	public void customSorting() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(7, 6, 5, 4, 4, 3, 1);

		Collections.sort(numbers, new ReverseNumericalOrder());
		assertEquals(expected, numbers);
	}

	@Test
	public void bubbleSort() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(1, 3, 4, 4, 5, 6, 7);

		Stopwatch timer = new Stopwatch();
		timer.start();
		assertEquals(expected, SortAndSearch.bubbleSort(Ints.toArray(numbers)));
		timer.stop();
		System.out.println("DiffTime : " + String.valueOf(timer.getElapsedMilliseconds()));

	}

	@Test
	public void insertSort() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(1, 3, 4, 4, 5, 6, 7);

		Stopwatch timer = new Stopwatch();
		timer.start();
		assertEquals(expected, SortAndSearch.insertSort(numbers));
		timer.stop();
		System.out.println("DiffTime : " + String.valueOf(timer.getElapsedMilliseconds()));
	}

	@Test
	public void quickSort() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(1, 3, 4, 4, 5, 6, 7);

		Stopwatch timer = new Stopwatch();
		timer.start();
		assertEquals(expected, SortAndSearch.quickSort(numbers));
		timer.stop();
		System.out.println("DiffTime : " + String.valueOf(timer.getElapsedMilliseconds()));
	}

	@Test
	public void mergeSort() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);
		final List<Integer> expected = Arrays.asList(1, 3, 4, 4, 5, 6, 7);

		Stopwatch timer = new Stopwatch();
		timer.start();
		assertEquals(expected, SortAndSearch.mergeSort(numbers));
		timer.stop();
		System.out.println("DiffTime : " + String.valueOf(timer.getElapsedMilliseconds()));
	}

	@Test
	public void binarySearch() {
		final List<Integer> numbers = Arrays.asList(4, 7, 1, 6, 3, 5, 4);

		List<Integer> result = SortAndSearch.quickSort(numbers);

		assertTrue(SortAndSearch.binarySearch(result, 3));
		assertFalse(SortAndSearch.binarySearch(result, 8));
	}

	class Person {
		private int age;

		Person(int age) {
			this.age = age;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

	class ReverseNumericalOrder implements Comparator<Integer> {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}

	static class SortAndSearch {
		public static List<Integer> bubbleSort(int[] numbers) {
			boolean numberSwitched;

			do {
				numberSwitched = false;
				for (int i = 0; i < numbers.length - 1; i++) {
					if (numbers[i + 1] < numbers[i]) {
						int tmp = numbers[i + 1];
						numbers[i + 1] = numbers[i];
						numbers[i] = tmp;
						numberSwitched = true;
					}
				}
			} while (numberSwitched);

			return Ints.asList(numbers);
		}

		public static List<Integer> insertSort(final List<Integer> numbers) {
			final List<Integer> sortedList = Lists.newLinkedList();

			originalList: for (Integer number : numbers) {
				for (int i = 0; i < sortedList.size(); i++) {
					if (number < sortedList.get(i)) {
						sortedList.add(i, number);
						continue originalList;
					}
				}

				sortedList.add(sortedList.size(), number);
			}

			return sortedList;
		}

		public static List<Integer> quickSort(List<Integer> numbers) {
			if (numbers.size() < 2) {
				return numbers;
			}

			final Integer pivot = numbers.get(0);
			final List<Integer> lower = Lists.newArrayList();
			final List<Integer> higher = Lists.newArrayList();

			for (int i = 1; i < numbers.size(); i++) {
				if (numbers.get(i) < pivot) {
					lower.add(numbers.get(i));
				} else {
					higher.add(numbers.get(i));
				}
			}

			final List<Integer> sorted = quickSort(lower);
			sorted.add(pivot);
			sorted.addAll(quickSort(higher));

			return sorted;
		}

		public static List<Integer> mergeSort(final List<Integer> numbers) {
			if (numbers.size() < 2) {
				return numbers;
			}

			final List<Integer> leftHalf = numbers.subList(0, numbers.size() / 2);
			final List<Integer> rightHalf = numbers.subList(numbers.size() / 2, numbers.size());

			return merge(mergeSort(leftHalf), mergeSort(rightHalf));
		}

		private static List<Integer> merge(final List<Integer> left, final List<Integer> right) {
			int leftPtr = 0;
			int rightPtr = 0;
			final List<Integer> merged = Lists.newArrayListWithCapacity(left.size() + right.size());
			
			while (leftPtr < left.size() && rightPtr < right.size()) {
				if (left.get(leftPtr) < right.get(rightPtr)) {
					merged.add(left.get(leftPtr));
					System.out.println("left : " + left.get(leftPtr));
					leftPtr++;
				} else {
					merged.add(right.get(rightPtr));
					System.out.println("right : " + right.get(rightPtr));
					rightPtr++;
				}
			}

			while (leftPtr < left.size()) {
				System.out.println("final left : " + left.get(leftPtr));
				merged.add(left.get(leftPtr));
				leftPtr++;
			}

			while (rightPtr < right.size()) {
				System.out.println("final right : " + right.get(rightPtr));
				merged.add(right.get(rightPtr));
				rightPtr++;
			}

			return merged;
		}

		public static boolean binarySearch(final List<Integer> numbers, final Integer searchValue) {
			if (numbers == null || numbers.isEmpty()) {
				return false;
			}

			final Integer comparison = numbers.get(numbers.size() / 2);
			if (searchValue.equals(comparison)) {
				return true;
			}

			if (searchValue < comparison) {
				return binarySearch(numbers.subList(0, numbers.size() / 2), searchValue);
			} else {
				return binarySearch(numbers.subList(numbers.size() / 2 + 1, numbers.size()), searchValue);
			}
		}
	}
}
