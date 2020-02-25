package ifStatements;

import java.util.Scanner;

import static java.lang.System.out;

class EvenOrOdd {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.print("Please enter a number");
		double a = in.nextDouble();
		if (a % 2 == 0) out.println(a + " is even.");
		else if (a % 2 != 0) out.println(a + " is odd.");
		in.close();
	}
}
