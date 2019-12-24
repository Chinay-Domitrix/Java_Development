import java.util.*;

151

public final class p151 extends EulerSolution {
	private final Map<List<Integer>, Double> expectedSingles = new HashMap<>();

	public static void main(String[] args) {
		System.out.println(new p151().run());
	}

	String run() {
		List<Integer> startState = Collections.singletonList(1);
		return String.format("%.6f", getExpectedSingles(startState) - 2);
	}

	private double getExpectedSingles(List<Integer> state) {
		if (expectedSingles.containsKey(state))
			return expectedSingles.get(state);

		double result = 0;
		if (!state.isEmpty()) {
			for (int i = 0; i < state.size(); i++) {
				List<Integer> newState = new ArrayList<>(state);
				int sheet = state.get(i);
				//noinspection SuspiciousListRemoveInLoop
				newState.remove(i);
				for (int j = sheet + 1; j <= 5; j++)
					newState.add(j);
				Collections.sort(newState);
				result += getExpectedSingles(newState);
			}
			result /= state.size();
			if (state.size() == 1)
				result++;
		}
		expectedSingles.put(state, result);
		return result;
	}
}