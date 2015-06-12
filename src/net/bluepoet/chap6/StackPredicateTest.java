package net.bluepoet.chap6;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class StackPredicateTest {
	private Stack stack;

	@Before
	public void setUp() {
		stack = new Stack();
		for (int i = 1; i <= 10; i++) {
			stack.push(i);
		}
	}

	@Test
	public void evenPredicate() {
		Stack filtered = stack.filter(new StackPredicate() {
			@Override
			public boolean isValid(int i) {
				return (i % 2 == 0);
			}
		});
		
		assertThat(10, is(filtered.pop()));
		assertThat(8, is(filtered.pop()));
		assertThat(6, is(filtered.pop()));
	}
	
	@Test
	public void allPredicate() {
		Stack filtered = stack.filter(new StackPredicate() {
			@Override
			public boolean isValid(int i) {
				return true;
			}
		});
		
		assertThat(10, is(filtered.pop()));
		assertThat(9, is(filtered.pop()));
		assertThat(8, is(filtered.pop()));
	}
	
	
}
