package projectEuler.programs;

import org.jetbrains.annotations.NotNull;

import static java.util.Arrays.stream;
import static projectEuler.programs.Library.listTotients;
import static projectEuler.programs.Library.pow;

public final class p072 extends EulerSolution {
	private static final int LIMIT = pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p072().run());
	}

	@NotNull String run() {
		int[] totients = listTotients(LIMIT);
		long sum = stream(totients, 2, totients.length).sum();
		return Long.toString(sum);
	}
}