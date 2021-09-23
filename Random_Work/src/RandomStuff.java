package Random_Work.src;

import java.util.Random;

import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

public class RandomStuff {
	public static void main(String[] args) {
	}

	public static String getAlphaNumericString(int n) {
		return range(0, n)
				.mapToObj(i -> valueOf("ABCDEFGHIJKLMNPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz".charAt(
						new Random().nextInt("ABCDEFGHIJKLMNPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz".length()))))
				.collect(joining());
	}
}
