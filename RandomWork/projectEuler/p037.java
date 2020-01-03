import org.jetbrains.annotations.NotNull;

public final class p037 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p037().run());
	}

	private static boolean isTruncatablePrime(int n) {
		// Test if left-truncatable
		for (long i = 10; i <= n; i *= 10) if (!Library.isPrime(n % (int) i)) return false;
		// Test if right-truncatable
		for (; n != 0; n /= 10) if (!Library.isPrime(n)) return false;
		return true;
	}

	@NotNull String run() {
		long sum = 0;
		for (int count = 0, n = 10; count < 11; n++)
			if (isTruncatablePrime(n)) {
				sum += n;
				count++;
			}
		return Long.toString(sum);
	}
}