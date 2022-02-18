package codeWars.sets;

import static java.lang.String.valueOf;
import static java.util.Arrays.stream;
import static java.util.stream.IntStream.rangeClosed;

/*
Your task is to sort a given string. Each word in the string will contain a single number character in a random position. This number is the position the word should have in the result.

Note: Numbers can be from 1 to 9. So 1 will be the first word (not 0).

If the input string is empty, return an empty string. The words in the input String will only contain valid consecutive numbers.
 */
public class Order {
	public static String order(String words) {
		String[] arr = words.split(" ");
		StringBuilder sb = new StringBuilder();
		rangeClosed(1, arr.length).forEach(i -> stream(arr).filter(s -> s.contains(valueOf(i))).forEach(s -> sb.append(s).append(' ')));
		return sb.toString().trim();
	}
}
