package net.bluepoet.chap7;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import net.bluepoet.common.TimerJob;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class FibonacciTest {
	private Map<Integer, Integer> fibCache = Maps.newHashMap();

	@Test
	public void fibonacciList() {
		assertEquals(0, fibonacci(0).size());
		assertEquals(Arrays.asList(0), fibonacci(1));
		assertEquals(Arrays.asList(0, 1), fibonacci(2));
		assertEquals(Arrays.asList(0, 1, 1), fibonacci(3));
		assertEquals(Arrays.asList(0, 1, 1, 2), fibonacci(4));
		assertEquals(Arrays.asList(0, 1, 1, 2, 3), fibonacci(5));
		assertEquals(Arrays.asList(0, 1, 1, 2, 3, 5), fibonacci(6));
		assertEquals(Arrays.asList(0, 1, 1, 2, 3, 5, 8), fibonacci(7));
		assertEquals(Arrays.asList(0, 1, 1, 2, 3, 5, 8, 13), fibonacci(8));
	}

	@Test(expected = IllegalArgumentException.class)
	public void fibN_음수일때예외던짐() {
		fibN(-1);
	}

	@Test
	public void fibN_cachedFibN_2미만숫자일때() {
		assertThat(0, is(fibN(0)));
		assertThat(1, is(fibN(1)));
		assertThat(0, is(cachedFibN(0)));
		assertThat(1, is(cachedFibN(1)));
	}

	@Test
	public void fibN_cachedFibN_2이상숫자일때() {
		assertThat(2, is(fibN(3)));
		assertThat(3, is(fibN(4)));
		assertThat(2, is(cachedFibN(3)));
		assertThat(3, is(cachedFibN(4)));
	}

	@Test
	public void fibN_cachedFibN_시간차이() {
		TimerJob fibNTimerJob = new TimerJob() {
			@Override
			protected void executeJob() {
				System.out.println(fibN(48));
			}
		};

		System.out.println("fibN Diff Time(s) : " + fibNTimerJob.execute().getElapsedSeconds());

		TimerJob cachedFibNTimerJob = new TimerJob() {
			@Override
			protected void executeJob() {
				System.out.println(cachedFibN(48));
			}
		};

		System.out.println("fibN Diff Time(s) : " + cachedFibNTimerJob.execute().getElapsedSeconds());
	}

	private List<Integer> fibonacci(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		if (n == 0) {
			return Lists.newArrayList();
		}

		if (n == 1) {
			return Lists.newArrayList(0);
		}

		if (n == 2) {
			return Lists.newArrayList(0, 1);
		}

		List<Integer> seq = Lists.newArrayListWithCapacity(n);
		seq.add(0);
		n = n - 1;
		seq.add(1);
		n = n - 1;

		while (n > 0) {
			int a = seq.get(seq.size() - 1);
			int b = seq.get(seq.size() - 2);
			seq.add(a + b);
			n = n - 1;
		}

		return seq;
	}

	private int fibN(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibN(n - 1) + fibN(n - 2);
	}

	private int cachedFibN(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must not be less than zero");
		}

		fibCache.put(0, 0);
		fibCache.put(1, 1);

		return recursiveCachedFibN(n);
	}

	private int recursiveCachedFibN(int n) {
		if (fibCache.containsKey(n)) {
			return fibCache.get(n);
		}

		int value = recursiveCachedFibN(n - 1) + recursiveCachedFibN(n - 2);
		fibCache.put(n, value);
		return value;
	}
}
