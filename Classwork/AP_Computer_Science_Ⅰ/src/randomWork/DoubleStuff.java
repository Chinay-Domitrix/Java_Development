package randomWork;

import static java.lang.Double.NaN;
import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static java.lang.System.out;

/**
 * This class is used to demonstrate how doubles behave under unusual
 * circumstances. Inspect the code and determine what it will do. - How is
 * division by zero handled? - Are theoretically equal doubles always equal in
 * practice? - How can setting a tolerance be used check equality with doubles?
 * Exercise: How many terms does it take for the series below to converge to
 * within 0.00001 of 2 1/1 + 1/2 + 1/4 + 1/8 + 1/16 + 1/32 + …
 */
public class DoubleStuff {
	public static void main(String[] args) {
		// Division By Zero
		out.println("10.0 / 0 = Infinity"); // positive double division by zero
		out.println("-10.0 / 0 = -Infinity"); // negative double division by zero
		out.printf("0.0 / 0 = %s%n%n", NaN); // zero double divided by zero
		// Limits of testing equality with doubles
		double one_seventhousandth = 1 / 7.0E3;
		out.println("1/7000 =" + one_seventhousandth);
		double three_seventhousandths = 3 / 7.0E3;
		out.println("3 / 7000 = " + three_seventhousandths);
		out.println("3 * 1/7000 == 3/7000? " + (3 * one_seventhousandth == three_seventhousandths)); // Why is this
																										// false?
		// Using tolerance to check "equality" to a certain degree
		double tolerance = 1.0E-10;
		out.println("3 * 1/7000 == 3/7000? " + (abs(3 * one_seventhousandth - three_seventhousandths) < tolerance));
		// Complete convergence exercise here
		out.println(summation());
	}

	private static double summation() {
		var sum = 0.0;
		int i = 0;
		while (abs(sum - 2) > 0.00001) {
			sum += pow(0.5, i);
			i++;
		}
		out.println(i + 1);
		return sum;
	}
}
