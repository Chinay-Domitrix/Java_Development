import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.iterate;

public class RandomStuff {
	public static void main(String[] args) {

	}

	public static String reverseBy2(String s) {
		return iterate(s.length(), i -> i >= (((s.length() % 2) == 0) ? 0 : 1), i -> i - 2).mapToObj(i -> s.substring(i, i + 2)).collect(joining());
	}
}