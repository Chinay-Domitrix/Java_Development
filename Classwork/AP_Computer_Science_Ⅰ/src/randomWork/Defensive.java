package randomWork;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.System.out;
import static java.util.Arrays.stream;

public class Defensive {
	private static final ArrayList<String> stringsAL = new ArrayList<>();

	public static void main(String... args) {
		var s = new String[4];
		s[0] = "AA";
		s[1] = "A";
		s[2] = "";
		stream(s).forEachOrdered(Defensive::accept);
		stringsAL.add(s[0]);
		stringsAL.add(s[1]);
		stringsAL.add(s[2]);
		stringsAL.add(s[3]);
		stringsAL.stream().filter(Objects::nonNull).mapToInt(String::length).forEachOrdered(out::println);
	}

	@Contract(value = "null -> fail", pure = true)
	private static void accept(String x) {
		assert x != null;
	}
}