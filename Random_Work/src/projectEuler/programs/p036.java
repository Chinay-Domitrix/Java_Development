package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.stream.IntStream;

import static projectEuler.programs.Library.isPalindrome;

public final class p036 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p036().run());
	}

	@NotNull String run() {
		long sum = IntStream.range(1, 1000000).filter(i -> isPalindrome(Integer.toString(i, 10)) && isPalindrome(Integer.toString(i, 2))).asLongStream().sum();
		return Long.toString(sum);
	}
}