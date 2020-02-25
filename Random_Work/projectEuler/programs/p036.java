package programs;

import org.jetbrains.annotations.NotNull;

public final class p036 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p036().run());
	}

	@NotNull String run() {
		long sum = 0;
		for (int i = 1; i < 1000000; i++)
			if (Library.isPalindrome(Integer.toString(i, 10)) && Library.isPalindrome(Integer.toString(i, 2)))
				sum += i;
		return Long.toString(sum);
	}
}