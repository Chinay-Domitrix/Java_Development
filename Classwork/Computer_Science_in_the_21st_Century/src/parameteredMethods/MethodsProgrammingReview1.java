package parameteredMethods;

import java.util.Scanner;

import static java.lang.System.out;

class MethodsProgrammingReview1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.println("Give two whole numbers to add.");
		int a = numSum1(in.nextInt(), in.nextInt());
		out.println(a);
		out.println("Give three whole numbers to add.");
		int b = numSum2(in.nextInt(), in.nextInt(), in.nextInt());
		out.println(b);
		out.println("Give four whole numbers to add.");
		int c = numSum3(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
		out.println(c);
		out.println("Give one whole number and one decimal to divide.");
		double d = numQuo(in.nextInt(), in.nextDouble());
		out.println(d);
		in.close();
	}

	private static int numSum1(int a, int b) {
		return a + b;
	}

	private static int numSum2(int a, int b, int c) {
		return a + b + c;
	}

	private static int numSum3(int a, int b, int c, int d) {
		return a + b + c + d;
	}

	private static double numQuo(int a, double b) {
		return a / b;
	}
}
