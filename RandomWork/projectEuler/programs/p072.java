package programs;

import org.jetbrains.annotations.NotNull;

public final class p072 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p072().run());
	}

	@NotNull String run() {
		long sum = 0;
		int[] totients = Library.listTotients(LIMIT);
		for (int i = 2; i < totients.length; i++) sum += totients[i];
		return Long.toString(sum);
	}
}