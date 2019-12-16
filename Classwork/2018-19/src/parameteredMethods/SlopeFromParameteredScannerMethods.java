package parameteredMethods;

import java.util.Scanner;

import static java.lang.System.out;

class SlopeFromParameteredScannerMethods {
	public static void main(String[] args) {
		double x1;
		double y1;
		double x2;
		double y2;
		try (Scanner points = new Scanner(System.in)) {
			out.println("What is the x-value for your first point?");
			x1 = firstX(points.nextDouble());
			out.println("What is the y-value for your first point?");
			y1 = firstY(points.nextDouble());
			out.println("What is the x-value for your second point?");
			x2 = secondX(points.nextDouble());
			out.println("What is the y-value for your second point?");
			y2 = secondY(points.nextDouble());
		}
		double slope = ((y2 - y1) / (x2 - x1));
		out.println("The slope of your line is approximately: " + slope);
	}

	private static double firstX(double a) {
		return a;
	}

	private static double firstY(double a) {
		return a;
	}

	private static double secondX(double a) {
		return a;
	}

	private static double secondY(double a) {
		return a;
	}
}
