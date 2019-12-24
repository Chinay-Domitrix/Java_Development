92

public final class p092 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 7);

	public static void main(String[] args) {
		System.out.println(new p092().run());
	}

	private static boolean isClass89(int x) {
		while (true) {
			switch (x) {
				case 1:
					return false;
				case 89:
					return true;
				default:
					x = nextNumber(x);
			}
		}
	}

	private static int nextNumber(int x) {
		int sum = 0;
		while (x != 0) {
			sum += (x % 10) * (x % 10);
			x /= 10;
		}
		return sum;
	}

	String run() {
		int count = 0;
		for (int i = 1; i < LIMIT; i++) {
			if (isClass89(i))
				count++;
		}
		return Integer.toString(count);
	}
}