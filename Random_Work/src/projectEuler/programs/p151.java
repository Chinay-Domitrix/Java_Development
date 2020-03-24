package projectEuler.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Collections.sort;
import static java.util.List.of;

public final class p151 extends EulerSolution {
	private final Map<List<Integer>, Double> expectedSingles = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(new p151().run());
	}

	String run() {
		var startState = of(1);
		return format("%.6f", getExpectedSingles(startState) - 2);
	}

	private double getExpectedSingles(List<Integer> state) {
		if (expectedSingles.containsKey(state)) return expectedSingles.get(state);
		double result = 0;
		if (!state.isEmpty()) {
			for (int i = 0; i < state.size(); i++) {
				var newState = new ArrayList<>(state);
				int sheet = state.get(i);
				newState.remove(i);
				for (int j = sheet + 1; j <= 5; j++) newState.add(j);
				sort(newState);
				result += getExpectedSingles(newState);
			}
			result /= state.size();
			if (state.size() == 1) result++;
		}
		expectedSingles.put(state, result);
		return result;
	}
}