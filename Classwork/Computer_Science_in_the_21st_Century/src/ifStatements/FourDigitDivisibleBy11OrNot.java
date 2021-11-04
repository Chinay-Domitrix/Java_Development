package ifStatements;

import java.util.Scanner;

import static java.lang.System.out;

class FourDigitDivisibleBy11OrNot {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.println("Please enter a four digit integer: ");
		determine(in.nextInt());
		in.close();
	}

	private static void determine(int x) {
		if (x >= 1000)
			if ((x % 2 == 0) && (x % 11 == 0))
				out.println("The number " + x + " is both even and divisible by 11.");
			else if ((x % 2 == 0) || (x % 11 == 0))
				out.println("The number " + x + " is either even or divisible by 11.");
			else
				out.println("The number " + x + " is neither even and divisible by 11.");
		else
			out.println("The number " + x + " is not four digits long.");
	}
}
