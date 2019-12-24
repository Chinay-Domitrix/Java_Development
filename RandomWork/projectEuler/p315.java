import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class p315 extends EulerSolution {
	// Mapping of [0, 10) -> [0x00, 0x7F); each output fits in 7 bits.
	private static final int[] DECIMAL_DIGIT_TO_SEGMENT = {0x77, 0x12, 0x5D, 0x5B, 0x3A, 0x6B, 0x6F, 0x72, 0x7F, 0x7B};

	public static void main(String[] args) {
		System.out.println(new p315().run());
	}

	private static int samTransitionsMinusMaxTransitions(int n) {
		int samTrans = 0;
		int maxTrans = 0;
		long segmentState = 0;
		while (true) {
			long newState = numberToSegments(n);
			if (newState == segmentState) break;
			maxTrans += Long.bitCount(newState ^ segmentState);
			segmentState = newState;
			samTrans += 2 * Long.bitCount(newState);
			n = digitSum(n);
		}
		maxTrans += Long.bitCount(segmentState);
		return samTrans - maxTrans;
	}

	@Contract(pure = true)
	private static long numberToSegments(int n) {
		if (n < 0 || n > 999999999) throw new IllegalArgumentException();
		long result = 0;
		int i = 0;
		do {
			result |= (long) DECIMAL_DIGIT_TO_SEGMENT[n % 10] << (i * 7);
			n /= 10;
			i++;
		} while (n != 0);
		return result;
	}

	// Also known as digital root.
	@Contract(pure = true)
	private static int digitSum(int n) {
		if (n < 0) throw new IllegalArgumentException();
		int result = 0;
		while (n != 0) {
			result += n % 10;
			n /= 10;
		}
		return result;
	}

	@NotNull String run() {
		boolean[] isPrime = Library.listPrimality(20000000);
		int sum = 0;
		for (int i = 10000000; i < isPrime.length; i++) if (isPrime[i]) sum += samTransitionsMinusMaxTransitions(i);
		return Integer.toString(sum);
	}
}