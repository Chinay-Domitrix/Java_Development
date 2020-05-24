package projectEuler.programs;

import java.util.stream.IntStream;

public final class p017 extends EulerSolution {
	private static final String[] ONES = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
	private static final String[] TENS = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

	public static void main(String[] args) {
		System.out.println(new p017().run());
	}

	private static String toEnglish(int n) {
		if (0 <= n && n < 20) return ONES[n];
		else if (20 <= n && n < 100) return TENS[n / 10] + (n % 10 != 0 ? ONES[n % 10] : "");
		else if (100 <= n && n < 1000)
			return ONES[n / 100] + "hundred" + (n % 100 != 0 ? "and" + toEnglish(n % 100) : "");
		else if (1000 <= n && n < 1000000)
			return toEnglish(n / 1000) + "thousand" + (n % 1000 != 0 ? toEnglish(n % 1000) : "");
		else throw new IllegalArgumentException();
	}

	/*
	 * - For the numbers 0 to 19, we write the single word:
	 * {zero, one, two, three, four, five, six, seven, eight, nine,
	 * ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}.
	 * - For the numbers 20 to 99, we write the word for the tens place:
	 * {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}.
	 * Subsequently if the last digit is not 0, then we write the word for the ones place (one to nine).
	 * - For the numbers 100 to 999, we write the ones word for the hundreds place followed by "hundred":
	 * {one hundred, two hundred, three hundred, ..., eight hundred, nine hundred}.
	 * Subsequently if the last two digits are not 00, then we write the word "and"
	 * followed by the phrase for the last two digits (from 01 to 99).
	 * - For the numbers 1000 to 999999, we write the word for the three digits starting at the
	 * thousands place and going leftward, followed by "thousand". Subsequently if the last three
	 * digits are not 000, then we write the phrase for the last three digits (from 001 to 999).
	 */
	String run() {
		int sum = IntStream.rangeClosed(1, 1000).map(i -> toEnglish(i).length()).sum();
		return Integer.toString(sum);
	}
}