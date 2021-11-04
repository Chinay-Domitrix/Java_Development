package bonusWork;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.String.format;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.Objects.requireNonNull;
import static java.util.stream.IntStream.range;

class VerticalSineWave {
	private static String startingSide;

	private VerticalSineWave() {
		out.println("This is a program that \"infinitely\" prints out a vertical sine wave.");
		out.print("Do you want to start on the left or right? I want to start on the ");
		try (Scanner input = new Scanner(in)) {
			startingSide = format("%s.", input.nextLine());
		}
	}

	private VerticalSineWave(String startingSide) throws IllegalStateException {
		VerticalSineWave.startingSide = startingSide;
		if (condition(startingSide, "right."))
			toLeft(4);
		else if (condition(startingSide, "left."))
			toRight(0);
		else
			throw new IllegalStateException(format("Unexpected value: %s", startingSide));
	}

	private VerticalSineWave(int min) {
		var startingSide = VerticalSineWave.startingSide;
		range(min, MAX_VALUE).forEachOrdered(a -> range(min, MAX_VALUE).forEachOrdered(b -> range(min, MAX_VALUE)
				.forEachOrdered(c -> range(min, MAX_VALUE).forEachOrdered(d -> range(min, MAX_VALUE).forEachOrdered(
						e -> range(min, MAX_VALUE).forEachOrdered(f -> range(min, MAX_VALUE).forEachOrdered(
								g -> range(min, MAX_VALUE).forEachOrdered(h -> range(min, MAX_VALUE)
										.forEachOrdered(i -> range(min, MAX_VALUE).forEachOrdered(
												j -> range(min, MAX_VALUE).forEachOrdered(k -> range(min, MAX_VALUE)
														.forEachOrdered(l -> range(min, MAX_VALUE).forEachOrdered(
																m -> range(min, MAX_VALUE).forEachOrdered(
																		n -> range(min, MAX_VALUE).forEachOrdered(
																				o -> range(min, MAX_VALUE)
																						.forEachOrdered(p -> range(min,
																								MAX_VALUE)
																								.forEachOrdered(
																										q -> range(
																												min,
																												MAX_VALUE)
																												.forEachOrdered(
																														r -> range(
																																min,
																																MAX_VALUE)
																																.forEachOrdered(
																																		s -> range(
																																				min,
																																				MAX_VALUE)
																																				.forEachOrdered(
																																						t -> range(
																																								min,
																																								MAX_VALUE)
																																								.forEachOrdered(
																																										u -> range(
																																												min,
																																												MAX_VALUE)
																																												.forEachOrdered(
																																														v -> range(
																																																min,
																																																MAX_VALUE)
																																																.forEachOrdered(
																																																		w -> range(
																																																				min,
																																																				MAX_VALUE)
																																																				.forEachOrdered(
																																																						x -> range(
																																																								min,
																																																								MAX_VALUE)
																																																								.forEachOrdered(
																																																										y -> range(
																																																												min,
																																																												MAX_VALUE)
																																																												.forEachOrdered(
																																																														z -> new VerticalSineWave(
																																																																startingSide)))))))))))))))))))))))))));
	}

	public static void main(String[] args) {
		new VerticalSineWave();
		range(MIN_VALUE, MAX_VALUE).forEachOrdered(a -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
				b -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(c -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
						d -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(e -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
								f -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(g -> range(MIN_VALUE, MAX_VALUE)
										.forEachOrdered(h -> range(MIN_VALUE, MAX_VALUE)
												.forEachOrdered(i -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
														j -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
																k -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
																		l -> range(MIN_VALUE, MAX_VALUE).forEachOrdered(
																				m -> range(MIN_VALUE, MAX_VALUE)
																						.forEachOrdered(n -> range(
																								MIN_VALUE, MAX_VALUE)
																								.forEachOrdered(
																										o -> range(
																												MIN_VALUE,
																												MAX_VALUE)
																												.forEachOrdered(
																														p -> range(
																																MIN_VALUE,
																																MAX_VALUE)
																																.forEachOrdered(
																																		q -> range(
																																				MIN_VALUE,
																																				MAX_VALUE)
																																				.forEachOrdered(
																																						r -> range(
																																								MIN_VALUE,
																																								MAX_VALUE)
																																								.forEachOrdered(
																																										s -> range(
																																												MIN_VALUE,
																																												MAX_VALUE)
																																												.forEachOrdered(
																																														t -> range(
																																																MIN_VALUE,
																																																MAX_VALUE)
																																																.forEachOrdered(
																																																		u -> range(
																																																				MIN_VALUE,
																																																				MAX_VALUE)
																																																				.forEachOrdered(
																																																						v -> range(
																																																								MIN_VALUE,
																																																								MAX_VALUE)
																																																								.forEachOrdered(
																																																										w -> range(
																																																												MIN_VALUE,
																																																												MAX_VALUE)
																																																												.forEachOrdered(
																																																														x -> range(
																																																																MIN_VALUE,
																																																																MAX_VALUE)
																																																																.forEachOrdered(
																																																																		y -> range(
																																																																				MIN_VALUE,
																																																																				MAX_VALUE)
																																																																				.forEachOrdered(
																																																																						z -> new VerticalSineWave(
																																																																								MIN_VALUE)))))))))))))))))))))))))));
	}

	private void toLeft(int i) {
		try {
			if (i == 0) {
				out.println("\t\t\t\t\t" + asterisks(i));
				toRight(i);
				return;
			}
			out.println("\t\t\t\t\t" + asterisks(i));
			toLeft(i - 1);
		} catch (StackOverflowError e) {
			new StackOverflowError().addSuppressed(e);
		}
	}

	private void toRight(int i) {
		try {
			if (i == 5) {
				toLeft(i - 1);
				return;
			}
			out.println("\t\t\t\t\t" + asterisks(i));
			toRight(i + 1);
		} catch (StackOverflowError e) {
			new StackOverflowError().addSuppressed(e);
		}
	}

	@NotNull
	@Contract(pure = true)
	private String asterisks(int i) {
		return (i != 0) ? (' ' + asterisks(i - 1)) : "*";
	}

	@Contract(value = "_, null -> false", pure = true)
	private boolean condition(@NotNull String checked, String condition) {
		return requireNonNull(checked).equalsIgnoreCase(condition);
	}
}
