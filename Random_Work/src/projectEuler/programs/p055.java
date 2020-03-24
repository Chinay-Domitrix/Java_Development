package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.stream.IntStream;

import static java.math.BigInteger.valueOf;

public final class p055 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p055().run());
	}

	private static boolean isLychrel(int n) {
		BigInteger temp = valueOf(n);
		for (int i = 0; i < 49; i++) {
			temp = temp.add(new BigInteger(Library.reverse(temp.toString())));
			if (Library.isPalindrome(temp.toString())) return false;
		}
		return true;
	}

	@NotNull String run() {
		int count = (int) IntStream.range(0, 10000).filter(p055::isLychrel).count();
		return Integer.toString(count);
	}
}