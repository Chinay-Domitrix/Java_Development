package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.pow;

public final class p079 extends EulerSolution {
	private static final String[] SUBSEQS = {"319", "680", "180", "690", "129", "620", "762", "689", "762", "318", "368", "710", "720", "710", "629", "168", "160", "689", "716", "731", "736", "729", "316", "729", "729", "710", "769", "290", "719", "680", "318", "389", "162", "289", "162", "718", "729", "319", "790", "680", "890", "362", "319", "760", "316", "729", "380", "319", "728", "716"};
	private char[] packedSubseqs;

	public static void main(String[] args) {
		System.out.println(new p079().run());
	}

	// Converts integer to string with zero padding, in little endian.
	// Since we're trying all combinations, the order doesn't matter.
	@NotNull
	@Contract(pure = true)
	private static char[] toChars(int n, int len) {
		char[] result = new char[len];
		int i = 0;
		for (; i < result.length; i++, n /= 10) result[i] = (char) ('0' + (n % 10));
		assert n == 0;
		return result;
	}

	String run() {
		// Preprocessing
		packedSubseqs = new char[SUBSEQS.length * 3];
		IntStream.range(0, packedSubseqs.length).forEachOrdered(i -> packedSubseqs[i] = SUBSEQS[i / 3].charAt(i % 3));
		// Try ascending lengths
		for (int len = 3; len <= 10; len++) {
			int end = pow(10, len);
			for (int guess = 0; guess < end; guess++) {
				char[] guessChars = toChars(guess, len);
				if (isConsistent(guessChars)) return new String(guessChars);
			}
		}
		throw new RuntimeException("Not found");
	}

	@Contract(pure = true)
	private boolean isConsistent(char[] guess) {
		// For each string 's' in SUBSEQS, test if 's' is a subsequence of 'guess'
		for (int i = 0; i < packedSubseqs.length; i += 3) {
			int j = 0; // Index in 's'
			// Index in 'guess'
			for (int k = 0; k < guess.length && j < 3; k++) if (guess[k] == packedSubseqs[i + j]) j++;
			// Not all characters consumed, fail
			if (j < 3) return false;
		}
		return true;
	}
}