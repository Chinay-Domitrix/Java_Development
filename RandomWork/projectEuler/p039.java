39

public final class p039 extends EulerSolution {
	public static void main(String[] args) {
		System.out.println(new p039().run());
	}

	private static int countSolutions(int p) {
		int count = 0;
		for (int a = 1; a <= p; a++) {
			for (int b = a; b <= p; b++) {
				int c = p - a - b;
				if (b <= c && a * a + b * b == c * c)
					count++;
			}
		}
		return count;
	}

	String run() {
		int maxPerimeter = 0;
		int maxTriangles = 0;
		for (int p = 1; p <= 1000; p++) {
			int triangles = countSolutions(p);
			if (triangles > maxTriangles) {
				maxTriangles = triangles;
				maxPerimeter = p;
			}
		}
		return Integer.toString(maxPerimeter);
	}
}