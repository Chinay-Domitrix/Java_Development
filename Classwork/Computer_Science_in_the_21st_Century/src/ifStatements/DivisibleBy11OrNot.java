package Classwork.Computer_Science_in_the_21st_Century.src.ifStatements;

import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class DivisibleBy11OrNot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.println("Please enter a number: ");
		determine(in.nextDouble());
		in.close();
	}

	private static void determine(double x) {
		if ((x % 2 == 0) && (x % 11 == 0)) out.println("The number " + x + " is both even and divisible by 11.");
		else if ((x % 2 == 0) || (x % 11 == 0))
			out.println("The number " + x + " is either even and divisible by 11.");
		else if ((x % 2 != 0) && (x % 11 != 0))
			out.println("The number " + x + " is neither even and divisible by 11.");
		else err.println("Error");
	}
}
