import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

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
				if (Library.isPalindrome(sum)) nums.add(sum);
			}
		}
		long sum = 0;
		for (int x : nums) sum += x;
		return Long.toString(sum);
	}
}