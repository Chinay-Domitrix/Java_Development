package Classwork.Computer_Science_in_the_21st_Century.src.parameteredMethods;

import static java.lang.System.out;

class StartingPractice {
	public static void main(String[] args) {
		double division = division(Math.random(), Math.random()), average = average(Math.random(), Math.random(), Math.random(), Math.random(), Math.random()), exponentialGrowthDouble = exponentialGrowthDouble(Math.random());
		int exponentialGrowthInt = exponentialGrowthInt((int) (Math.random() * ((100 - 1) + 1)) + 1);
		out.println(division + "\n" + average + "\n" + exponentialGrowthInt + "\n" + exponentialGrowthDouble);
	}

	private static double division(double a, double d) {
		return a / d;
	}

	private static double average(double d, double e, double f, double g, double h) {
		return (d + e + f + g + h) / 5;
	}

	private static int exponentialGrowthInt(double d) {
		return (int) Math.pow(d, 4);
	}

	private static double exponentialGrowthDouble(double a) {
		return Math.pow(a, 4);
	}
}
