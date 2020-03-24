package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

import static projectEuler.programs.Library.isPalindrome;

public final class p125 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p125().run());
	}

	@NotNull String run() {
		HashSet<Integer> nums = new HashSet<>();
		for (int i = 1; i <= 10000; i++) {
			int sum = i * i;
			for (int j = i + 1; ; j++) {
				sum += j * j;
				if (sum >= 100000000) break;
				if (isPalindrome(sum)) nums.add(sum);
			}
		}
		long sum = nums.stream().mapToInt(x -> x).asLongStream().sum();
		return Long.toString(sum);
	}
}