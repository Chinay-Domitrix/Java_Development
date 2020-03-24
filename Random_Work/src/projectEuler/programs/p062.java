package projectEuler.programs;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.util.HashMap;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Math.min;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.sort;

public final class p062 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p062().run());
	}

	@NotNull
	@Contract("_ -> new")
	private static String getCubeNumberClass(int x) {
		char[] digits = cube(x).toString().toCharArray();
		sort(digits);
		return new String(digits);
	}

	private static BigInteger cube(int x) {
		return valueOf(x).pow(3);
	}

	String run() {
		int numDigits = 0;
		HashMap<String, Integer> lowest = new HashMap<>(), counts = new HashMap<>();
		for (int i = 0; ; i++) {
			String numClass = getCubeNumberClass(i);
			if (numClass.length() > numDigits) {
				// Process and flush data for smaller number of digits
				int min = MAX_VALUE;
				for (String nc : counts.keySet()) if (counts.get(nc) == 5) min = min(lowest.get(nc), min);
				if (min != MAX_VALUE) return cube(min).toString();
				lowest.clear();
				counts.clear();
				numDigits = numClass.length();
			}
			if (!lowest.containsKey(numClass)) {
				lowest.put(numClass, i);
				counts.put(numClass, 0);
			}
			counts.put(numClass, counts.get(numClass) + 1);
		}
	}
}