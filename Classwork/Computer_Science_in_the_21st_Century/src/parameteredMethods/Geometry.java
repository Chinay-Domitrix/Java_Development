package Classwork.Computer_Science_in_the_21st_Century.src.parameteredMethods;

import java.util.Scanner;

import static java.lang.System.out;

class Geometry {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.println("What do you want to do: area, perimeter, or circumference? (all lowercase please)");
			String ans1 = in.nextLine();
			switch (ans1) {
				case "area":
					out.println("Area of a rectangle, a square, or a triangle? (all lowercase please)");
					String ans2 = in.nextLine();
					switch (ans2) {
						case "rectangle":
						case "square":
							out.println("Please enter the base and height, in that exact order.");
							double a = rectArea(in.nextDouble(), in.nextDouble());
							out.println("Your answer is " + a + " units");
							break;
						case "triangle":
							out.println("Please enter the base and height, in that exact order.");
							double c = triArea(in.nextDouble(), in.nextDouble());
							out.println("Your answer is " + c + " units");
							break;
					}
					break;
				case "perimeter":
					out.println("This outputs the perimeter of a square.");
					String ans3 = in.nextLine();
					if (ans3.equals("square")) {
						out.println("Please enter a side length.");
						double d = squarePeri(in.nextDouble());
						out.println("Your answer is " + d + " units");
					}
					break;
				case "circumference":
					out.println("Do you have the diameter or the radius? (all lowercase please)");
					String ans4 = in.nextLine();
					if (ans4.equals("radius")) {
						out.println("Please enter the radius length.");
						double e = circRadi(in.nextDouble());
						out.println("Your answer is " + e + " units");
					} else if (ans4.equals("diameter")) {
						out.println("Please enter the diameter length.");
						double f = circDiam(in.nextDouble());
						out.println("Your answer is " + f + " units");
					}
					break;
			}
		}
	}

	private static double rectArea(double b, double h) {
		return Math.round((b * h) * 100) / 100.0;
	}

	private static double triArea(double b, double h) {
		return Math.round((0.5 * b * h) * 100) / 100.0;
	}

	private static double squarePeri(double s) {
		return Math.round((s * 4) * 100) / 100.0;
	}

	private static double circDiam(double d) {
		return Math.round((Math.PI * d) * 100) / 100.0;
	}

	private static double circRadi(double r) {
		return Math.round((2 * Math.PI * r) * 100) / 100.0;
	}
}
