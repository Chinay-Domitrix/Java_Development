package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;
import static java.util.Arrays.fill;
import static java.util.stream.IntStream.range;

public final class p215 extends EulerSolution {
	private static final int WIDTH = 32;
	private static final int HEIGHT = 10;

	public static void main(String[] args) {
		System.out.println(new p215().run());
	}

	private static void getCrackPositions(Stack<Integer> cracks, int position, List<int[]> result) {
		if (position < 0) throw new IllegalArgumentException();
		else if (position < WIDTH) {
			for (int i = 2; i <= 3; i++) {
				cracks.push(position + i);
				getCrackPositions(cracks, position + i, result);
				cracks.pop();
			}
		} else if (position == WIDTH) {
			result.add(range(0, cracks.size() - 1).map(cracks::get).toArray());
		} // position > WIDTH

	}

	@Contract(pure = true)
	private static boolean areDisjointSorted(@NotNull int[] a, int[] b) {
		for (int i = 0, j = 0; (i < a.length) && (j < b.length); )
			if (a[i] == b[j]) return false;
			else if (a[i] < b[j]) i++;
			else if (a[i] > b[j]) j++;
			else throw new AssertionError();
		return true;
	}

	String run() {
		var crackPositions = new ArrayList<int[]>();
		getCrackPositions(new Stack<>(), 0, crackPositions);
		BigInteger[] ways = new BigInteger[crackPositions.size()];
		fill(ways, ONE);
		for (int i = 1; i < HEIGHT; i++) {
			BigInteger[] newWays = new BigInteger[ways.length];
			for (int j = 0; j < newWays.length; j++) {
				BigInteger sum = ZERO;
				for (int k = 0; k < ways.length; k++)
					if (areDisjointSorted(crackPositions.get(j), crackPositions.get(k))) sum = sum.add(ways[k]);
				newWays[j] = sum;
			}
			ways = newWays;
		}
		var sum = ZERO;
		for (var x : ways) sum = sum.add(x);
		return sum.toString();
	}
}