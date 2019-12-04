package parameteredMethods;

import java.util.Scanner;

import static java.lang.System.out;

class TrapezoidAreaFromParameteredScannerMethods {
	public static void main(String[] args) {
		double a;
		double b;
		double h;
		try (Scanner trapezoid = new Scanner(System.in)) {
			out.print("Base 1: ");
			a = base1(trapezoid.nextDouble());
			out.print("Base 2: ");
			b = base2(trapezoid.nextDouble());
			out.print("Height: ");
			h = height(trapezoid.nextDouble());
		}
		double area = (((a + b) / 2) * h);
		out.println("The area of the trapezoid is " + area + " square units.");
	}

	private static double base1(double a) {
		return a;
	}

	private static double base2(double a) {
		return a;
	}

	private static double height(double a) {
		return a;
	}
}
