package net.bluepoet.chap7;

public class FizzBuzz {
	public static void main(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("NO INPUT NUMBER!");
		}

		int inputNumber = Integer.valueOf(args[0]);
		String word = null;

		for (int i = 1; i <= inputNumber; i++) {
			word = printWordByDividedNumber(i, 3, "Fizz") + printWordByDividedNumber(i, 5, "Buzz");

			if (word.equals(""))
				System.out.println(i);
			else
				System.out.println(word);
		}
	}

	private static String printWordByDividedNumber(int number, int divider, String word) {
		if (number % divider == 0)
			return word;
		else
			return "";
	}
}
