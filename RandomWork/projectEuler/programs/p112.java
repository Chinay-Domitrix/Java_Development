package programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p112 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p112().run());
	}

	@Contract(pure = true)
	private static boolean isBouncy(int x) {
		if (x < 100) return false;
		else {
			boolean nonincreasing = true, nondecreasing = true;
			int lastDigit = x % 10;
			x /= 10;
			while (x != 0) {
				int digit = x % 10;
				if (digit > lastDigit) nondecreasing = false;
				else if (digit < lastDigit) nonincreasing = false;
				lastDigit = digit;
				x /= 10;
			}
			return !nonincreasing && !nondecreasing;
		}
	}

	@NotNull String run() {
		int bouncy = 0;
		for (int i = 1; ; i++) {
			if (isBouncy(i)) bouncy++;
			if (bouncy * 100 == i * 99) return Integer.toString(i);
		}
	}
}