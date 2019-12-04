package whileLoops;

import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.System.out;

class WhileLoopsPractice1 {
	public static void main(String[] args) {
		int n;
		try (Scanner in = new Scanner(System.in)) {
			out.print("Enter a value: ");
			n = in.nextInt();
		}
		out.print("The powers of " + n + " that are less than 10000 are ");
		int p = n;
		while (p <= 10000) {
			if ((p * pow(n, 2)) < 10000) out.print(p + ", ");
			else if ((p * n) > 10000) out.print(p + ".");
			else out.print(p + ", and ");
			p *= n;
		}
	}
}
