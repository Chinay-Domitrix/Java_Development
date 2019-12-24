import java.math.BigInteger;

55

public final class p055 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p055().run());
	}

	private static boolean isLychrel(int n) {
		BigInteger temp = BigInteger.valueOf(n);
		for (int i = 0; i < 49; i++) {
			temp = temp.add(new BigInteger(Library.reverse(temp.toString())));
			if (Library.isPalindrome(temp.toString()))
				return false;
		}
		return true;
	}

	String run() {
		int count = 0;
		for (int i = 0; i < 10000; i++) {
			if (isLychrel(i))
				count++;
		}
		return Integer.toString(count);
	}
}