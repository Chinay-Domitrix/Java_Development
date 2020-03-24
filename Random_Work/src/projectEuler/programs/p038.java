package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.util.Arrays.sort;

public final class p038 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p038().run());
	}

	private static boolean isPandigital(@NotNull String s) {
		if (s.length() != 9) return false;
		char[] temp = s.toCharArray();
		sort(temp);
		return new String(temp).equals("123456789");
	}

	@NotNull String run() {
		int max = -1;
		for (int n = 2; n <= 9; n++)
			for (int i = 1; i < Library.pow(10, 9 / n); i++) {
				var concat = new StringBuilder();
				for (int j = 1; j <= n; j++) concat.append(i * j);
				if (isPandigital(concat.toString())) max = max(parseInt(concat.toString()), max);
			}
		return Integer.toString(max);
	}
}