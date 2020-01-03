package programs;

import static java.lang.Math.*;

public final class p431 extends EulerSolution {
	private static final double RADIUS = 6;
	private static final double REPOSE = toRadians(40);

	public static void main(String[] args) {
		System.out.println(new p431().run());
	}

	private static double findRootSecant(double x0, double x1, double val) {
		// Secant method with adaptive precision for numerical integration
		int samples = 10000;
		double y0 = volume(x0, samples);
		double y1 = volume(x1, samples);
		while (abs(x0 - x1) > 1e-12) {
			double x2 = (val - y0) / (y1 - y0) * (x1 - x0) + x0;
			double y2 = volume(x2, samples);
			x0 = x1;
			x1 = x2;
			y0 = y1;
			y1 = y2;
			if (abs(x0 - x1) < 1e-4) samples = max(1000000, samples);
			if (abs(x0 - x1) < 1e-6) samples = max(10000000, samples);
			if (abs(x0 - x1) < 1e-8) samples = max(100000000, samples);
		}
		return x1;
	}

	private static double volume(double x, int samples) {
		double discVolume = pow(RADIUS + x, 3) / 3 * PI * 2;
		if (x > 0) {
			// Precomputed constants
			double scaler = x * 2 / samples;
			double r2plusx2 = RADIUS * RADIUS + x * x;
			double rec2x = 1 / (x * 2);
			// Integrate using midpoint rule with uniform partition size
			double sum = 0;
			for (int i = 0; i < samples; i++) {
				double r = RADIUS - x + (i + 0.5) * scaler;
				double r2 = r * r;
				sum += acos(((r2plusx2 - r2) * rec2x - x) / r) * r2;
			}
			discVolume -= sum * 4 * x / samples;
		}
		return discVolume * tan(REPOSE);
	}

	String run() {
		double sum = 0;
		for (int i = 20; i <= 25; i++) sum += findRootSecant(0, RADIUS, i * i);
		return String.format("%.9f", sum);
	}
}