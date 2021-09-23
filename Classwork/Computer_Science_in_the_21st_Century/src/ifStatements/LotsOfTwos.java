package Classwork.Computer_Science_in_the_21st_Century.src.ifStatements;

import java.util.Scanner;

import static java.lang.System.err;
import static java.lang.System.out;

class LotsOfTwos {
	public static void main(String[] args) {
		twos();
	}

	private static void twos() {
		Scanner in = new Scanner(System.in);
		String a = "Please enter a number: ";
		out.print(a);
		double b = in.nextDouble(), e;
		String c = "Would you like to (a)dd 2, (s)ubtract 2, (m)ultiply by 2, (d)ivide by 2, or (r)aise to the 2nd power: ";
		out.print(c);
		char d = in.next().charAt(0);
		if (d == 'a') {
			e = b + 2;
			out.println("The result when adding 2 to " + b + " is " + e + ".");
		} else if (d == 's') {
			e = b - 2;
			out.println("The result when subtracting 2 from " + b + " is " + e + ".");
		} else if (d == 'm') {
			e = b * 2;
			out.println("The result when multiplying " + b + " by 2 is " + e + ".");
		} else if (d == 'd') {
			e = b / 2;
			out.println("The result when dividing " + b + " by 2 is " + e + ".");
		} else if (d == 'r') {
			e = Math.pow(b, 2);
			out.println("The result when raising " + b + " by 2 is " + e + ".");
		} else err.println("Error...");
		in.close();
	}
}
