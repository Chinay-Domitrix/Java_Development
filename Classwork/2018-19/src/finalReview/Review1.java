package finalReview;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.*;
import static java.util.Arrays.setAll;

class Review1 {
	private static final Scanner in = new Scanner(System.in);

	public static void main(final String[] args) {
		final int[] x = new int[(int) (Math.random() * 100 + 1)];
		setAll(x, i -> (int) (Math.random() * 10 + 1));
		out.print("Which task do you want to run? ");
		switch (in.nextInt()) {
			default:
				err.println("Error.");
				break;
			case 1:
				t1();
				break;
			case 2:
				t2();
				break;
			case 3:
				t3();
				break;
			case 4:
				out.println(t4(x));
				break;
			case 5:
				t5();
				break;
			case 6:
				out.println("The sum is " + t6(x));
				break;
			case 7:
				out.println("The average is " + t7(x));
		}
	}

	private static void t1() {
		out.print("Which loop type do you want to run? ");
		final String lT = in.nextLine();
		int x = 0;
		if (lT.equalsIgnoreCase("for"))
			x = 1;
		else if (lT.equalsIgnoreCase("while"))
			x = 2;
		else if (lT.equalsIgnoreCase("do/while") || lT.equalsIgnoreCase("do-while") || lT.equalsIgnoreCase("do while"))
			x = 3;
		int i = 2;
		switch (x) {
			default:
				err.println("Error.");
				break;
			case 1:
				for (; i < 50; i += 2)
					out.print(i + ' ');
				break;
			case 2:
				while (i < 50) {
					out.print(i + ' ');
					i += 2;
				}
				break;
			case 3:
				do {
					out.print(i + ' ');
					i += 2;
				} while (i < 50);
				break;
		}
	}

	private static void t2() {
		out.print("Which loop type do you want to run? ");
		final String lT = in.nextLine();
		int x = 0;
		if (lT.equalsIgnoreCase("for"))
			x = 1;
		else if (lT.equalsIgnoreCase("while"))
			x = 2;
		else if (lT.equalsIgnoreCase("do/while") || lT.equalsIgnoreCase("do-while") || lT.equalsIgnoreCase("do while"))
			x = 3;
		int i = 0;
		int sum = 0;
		out.println("Please enter five ints.");
		switch (x) {
			default:
				out.println("Error.");
				break;
			case 1:
				for (; i < 5; i++)
					sum += in.nextInt();
				break;
			case 2:
				while (i < 5) {
					sum += in.nextInt();
					i++;
				}
				break;
			case 3:
				do {
					sum += in.nextInt();
					i++;
				} while (i < 5);
				break;
		}
		out.println("The sum was " + sum);
	}

	private static void t3() {
		int i1 = 1;
		while (i1 < 4) {
			int j1 = 0;
			while (j1 <= i1) {
				out.print("*");
				j1++;
			}
			out.println();
			i1++;
		}
		out.println();
		for (int i2 = 0; i2 < 4; i2++) {
			for (int j2 = 0; j2 < 4; j2++)
				out.print(i2);
			out.println();
		}
		out.println();
		int t3P3Counter = 4;
		for (int i3 = 1; i3 < 4; i3++) {
			for (int j3 = 0; j3 <= i3; j3++)
				out.print(t3P3Counter);
			t3P3Counter++;
			out.println();
		}
		final int[] x = {9, 8, 7, 6};
		for (int i4 = x.length - 1; i4 >= 0; i4--) {
			for (int j4 = 0; j4 <= i4; j4++)
				out.print(x[j4]);
			out.println();
		}
		out.println();
	}

	private static boolean t4(final int[] x) {
		final int[] y = new int[x.length];
		for (int i = 1; i < y.length; i++) {
			int j = i;
			while (j > 0 && y[j] < y[j - 1]) {
				final int ph = y[j];
				y[j] = y[j - 1];
				y[j - 1] = ph;
				j--;
			}
		}
		arraycopy(x, 0, y, 0, x.length);
		return Arrays.toString(x).equals(Arrays.toString(y));
	}

	private static void t5() {
		out.println("a b c");
		out.println("d e f");
		out.print("g");
		for (int i = 0; i < 2; i++)
			out.print("\t");
		out.println("h");
		out.print("i");
		out.println("j");
	}

	private static int t6(final int[] x) {
		int sum = 0;
		for (final int y : x)
			if (y % 2 == 0)
				sum += y;
		if (x.length > 10)
			sum *= 2;
		return sum;
	}

	private static double t7(final int[] x) {
		int counter = 0;
		double sum = 0;
		for (int i = 3; i < x.length; i += 3) {
			sum += x[i];
			counter++;
		}
		sum /= counter;
		return sum;
	}

	@Override
	public boolean equals(final Object obj) {
		return super.equals(obj);
	}
}