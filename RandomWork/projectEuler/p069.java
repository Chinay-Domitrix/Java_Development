69

public final class p069 extends EulerSolution {
	private static final int LIMIT = Library.pow(10, 6);

	public static void main(String[] args) {
		System.out.println(new p069().run());
	}

	String run() {
		int maxNumer = 0;
		int maxDenom = 1;
		int[] totients = Library.listTotients(LIMIT);
		for (int n = 1; n < totients.length; n++) {
			if ((long) n * maxDenom > (long) maxNumer * totients[n]) {
				maxNumer = n;
				maxDenom = totients[n];
			}
		}
		return Integer.toString(maxNumer);
	}
}