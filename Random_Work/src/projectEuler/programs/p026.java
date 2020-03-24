package projectEuler.programs;

import java.util.HashMap;
import java.util.Map;

public final class p026 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p026().run());
	}

	private static int getCycleLength(int n) {
		Map<Integer, Integer> stateToIter = new HashMap<>();
		int state = 1;
		for (int iter = 0; ; iter++)
			if (stateToIter.containsKey(state)) return iter - stateToIter.get(state);
			else {
				stateToIter.put(state, iter);
				state = (state * 10) % n;
			}
	}

	String run() {
		int bestNumber = 0;
		int bestLength = 0;
		for (int i = 1; i <= 1000; i++) {
			int len = getCycleLength(i);
			if (len > bestLength) {
				bestNumber = i;
				bestLength = len;
			}
		}
		return Integer.toString(bestNumber);
	}
}