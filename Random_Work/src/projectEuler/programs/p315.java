package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Long.bitCount;
import static java.util.stream.IntStream.range;
import static projectEuler.programs.Library.listPrimality;

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
			maxTrans += bitCount(newState ^ segmentState);
			segmentState = newState;
			samTrans += 2 * bitCount(newState);
			n = digitSum(n);
		}
		maxTrans += bitCount(segmentState);
		return samTrans - maxTrans;
	}

	@Contract(pure = true)
	private static long numberToSegments(int n) {
		assert (n >= 0) && (n <= 999999999);
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
		assert n >= 0;
		int result = 0;
		while (n != 0) {
			result += n % 10;
			n /= 10;
		}
		return result;
	}

	@NotNull String run() {
		boolean[] isPrime = listPrimality(20000000);
		int sum = range(10000000, isPrime.length).filter(i -> isPrime[i]).map(p315::samTransitionsMinusMaxTransitions).sum();
		return Integer.toString(sum);
	}
}