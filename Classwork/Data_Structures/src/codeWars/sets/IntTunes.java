package codeWars.sets;

import java.util.ArrayList;

import static java.util.Arrays.stream;
import static java.util.List.of;
import static java.util.stream.Collectors.toCollection;

public class IntTunes {
	public static boolean isTune(int[] notes) {
		if ((notes == null) || (notes.length == 0)) return false;
		return stream(notes).anyMatch(i -> of(-10, -8, -6, -5, -3, -1, 0, 2, 4, 5, 6, 7, 9, 11).containsAll(stream(notes).mapToObj(j -> (j - i) % 12).collect(toCollection(ArrayList::new))));
	}
}
