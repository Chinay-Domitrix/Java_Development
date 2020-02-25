package programs;

import org.jetbrains.annotations.NotNull;

public final class p020 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p020().run());
	}

	/*
	 * We do a straightforward product with help from Java's BigInteger type.
	 */
	@NotNull String run() {
		String temp = Library.factorial(100).toString();
		int sum = 0;
		for (int i = 0; i < temp.length(); i++) sum += temp.charAt(i) - '0';
		return Integer.toString(sum);
	}
}
