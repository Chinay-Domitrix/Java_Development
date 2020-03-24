package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public final class p095 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p095().run());
	}

	@NotNull String run() {
		// divisorSum[n] is the sum of all the proper divisors of n
		int[] divisorSum = new int[LIMIT + 1];
		for (int i = 1; i <= LIMIT; i++) for (int j = i * 2; j <= LIMIT; j += i) divisorSum[j] += i;
		// Analyze the amicable chain length for each number in ascending order
		int maxChainLen = 0;
		int minChainElem = -1;
		for (int i = 0; i <= LIMIT; i++) {
			HashSet<Integer> visited = new HashSet<>();
			for (int count = 1, cur = i; ; count++) {
				// 'count' is the length of the this amicable chain
				visited.add(cur);
				int next = divisorSum[cur];
				if (next == i) {
					if (count > maxChainLen) {
						minChainElem = i;
						maxChainLen = count;
					}
					break;
				}
				// Exceeds limit or not a chain (a rho shape instead)
				else if ((next > LIMIT) || visited.contains(next)) break;
				else cur = next;
			}
		}
		return Integer.toString(minChainElem);
	}
}