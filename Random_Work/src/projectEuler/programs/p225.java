package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public final class p225 extends EulerSolution {
	private static final int INDEX = 124; // 1-based

	public static void main(String[] args) {
		System.out.println(new p225().run());
	}

	// Tests whether any term of the Tribonacci sequence [1, 1, 1, 3, 5, 9, 17, 31, ...]
	// is a multiple of 'modulus'. Although the mathematical sequence is infinitely long,
	// this function returns an answer in O(modulus^3) time.
	private static boolean hasTribonacciMultiple(int modulus) {
		// Floyd's cycle-finding algorithm
		int[] slow = {1, 1, 1};
		int[] fast = slow.clone();
		for (boolean head = true; ; head = false) {
			if (slow[0] % modulus == 0) return true;
			if (!head && Arrays.equals(slow, fast)) return false;
			tribonacci(slow, modulus);
			tribonacci(fast, modulus);
			tribonacci(fast, modulus);
		}
	}

	// Advances the 3-element Tribonacci state vector by one iteration in place.
	private static void tribonacci(@NotNull int[] state, int mod) {
		int a = state[0];
		int b = state[1];
		int c = state[2];
		state[0] = b;
		state[1] = c;
		state[2] = (a + b + c) % mod;
	}

	@NotNull String run() {
		int count = 0;
		for (int i = 1; ; i += 2)
			if (!hasTribonacciMultiple(i)) {
				count++;
				if (count == INDEX) return Integer.toString(i);
			}
	}
}