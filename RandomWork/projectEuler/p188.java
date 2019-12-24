188

public final class p188 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p188().run());
	}

	private static int tetrationMod(int x, int y, int m) {
		if (y == 1)
			return x % m;
		else
			// Fact: If x and m are coprime, then x^y mod m = x^(y mod totient(m)) mod m
			return Library.powMod(x, tetrationMod(x, y - 1, Library.totient(m)), m);
	}

	String run() {
		return Integer.toString(tetrationMod(1777, 1855, Library.pow(10, 8)));
	}
}