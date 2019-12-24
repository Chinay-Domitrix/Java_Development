import java.util.Arrays;

52

public final class p052 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p052().run());
	}

	private static boolean multiplesHaveSameDigits(int x) {
		for (int i = 2; i <= 6; i++) {
			if (!Arrays.equals(toSortedDigits(x), toSortedDigits(i * x)))
				return false;
		}
		return true;
	}

	private static char[] toSortedDigits(int x) {
		char[] result = Integer.toString(x).toCharArray();
		Arrays.sort(result);
		return result;
	}

	String run() {
		for (int i = 1; ; i++) {
			if (i > Integer.MAX_VALUE / 6)
				throw new ArithmeticException("Overflow");
			if (multiplesHaveSameDigits(i))
				return Integer.toString(i);
		}
	}
}