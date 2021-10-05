package remoteLearning.march12;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Character.isLowerCase;
import static java.lang.Character.toLowerCase;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.IntStream.range;

final class Randomize {
	private final String toJumble;
	private int shift;

	private Randomize(String toJumble) {
		this.toJumble = toJumble;
		shift = new Random().nextInt(toJumble.length()) + 1;
	}

	private Randomize(String toJumble, int shift) {
		this(toJumble);
		this.shift = shift;
	}

	public final String jumble() {
		var chars = range(0, toJumble.length()).mapToObj(i -> toJumble.toUpperCase().charAt(i))
				.collect(toCollection(ArrayList::new));
		range(0, chars.size()).forEachOrdered(i -> chars.set(i,
				(char) ((((int) chars.get(i) + shift) > 90) ? ((chars.get(i) + shift) - 26) : (chars.get(i) + shift))));
		return range(0, chars.size()).mapToObj(
						i -> valueOf(isLowerCase(toJumble.charAt(i)) ? valueOf(toLowerCase(chars.get(i))) : chars.get(i)))
				.collect(joining());
	}
}
