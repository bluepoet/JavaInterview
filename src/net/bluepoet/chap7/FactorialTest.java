package net.bluepoet.chap7;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class FactorialTest {

	@Test
	public void factorial() throws Exception {
		// Given
		// When
		long result = FactorialCalculator.factorial(5);

		// Then
		assertThat(result).isEqualTo(120L);
	}

	@Test
	public void recursiveFactorial() throws Exception {
		// Given
		// When
		long result = FactorialCalculator.recursiveFactorial(5);

		// Then
		assertThat(result).isEqualTo(120L);
	}
}

class FactorialCalculator {
	public static long factorial(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("n must be greater than zero");
		}

		long toReturn = 1;
		for (int i = 1; i <= n; i++) {
			toReturn *= i;
		}

		return toReturn;
	}

	public static long recursiveFactorial(int n) {
		if (n < 1) {
			throw new IllegalArgumentException("n must be greater than zero");
		}

		if (n == 1)
			return 1;
		else
			return n * recursiveFactorial(n - 1);
	}
}