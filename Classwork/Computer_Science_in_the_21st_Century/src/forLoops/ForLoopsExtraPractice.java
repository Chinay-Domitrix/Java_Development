package forLoops;

import static java.lang.System.out;

class ForLoopsExtraPractice {
	public static void main(String[] args) {
		// Line 1
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 20; j++)
				out.print("*");
			spacing(3);
		}
		spacing(1);
		for (int i = 0; i < 25; i++)
			out.print("*");
		out.println();

		// Line 2
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(19);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(23);
		out.print("*");
		out.println();

		// Line 3
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(20);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(21);
		out.print("*");
		out.println();

		// Line 4
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(21);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(19);
		out.print("*");
		out.println();

		// Line 5
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(17);
		out.print("*");
		out.println();

		// Line 6
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(23);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(15);
		out.print("*");
		out.println();
		// Line 7
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(5);
		out.print("*");
		spacing(13);
		out.print("*");
		out.println();
		// Line 8
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(21);
		out.print("*");
		spacing(7);
		out.print("*");
		spacing(11);
		out.print("*");
		out.println();
		// Line 9
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(20);
		out.print("*");
		spacing(9);
		out.print("*");
		spacing(9);
		out.print("*");
		out.println();
		// Line 10
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(19);
		out.print("*");
		spacing(11);
		out.print("*");
		spacing(7);
		out.print("*");
		out.println();
		// Line 11
		out.print("*");
		spacing(22);
		for (int i = 0; i < 20; i++)
			out.print("*");
		out.print("*");
		spacing(12);
		for (int i = 0; i < 7; i++)
			out.print("*");
		out.println();
		// Line 12
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(19);
		out.print("*");
		spacing(3);
		for (int i = 0; i < 25; i++)
			out.print("*");
		out.println();
		// Line 13
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(20);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(21);
		out.print("*");
		out.println();
		// Line 14
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(21);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(19);
		out.print("*");
		out.println();
		// Line 15
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(17);
		out.print("*");
		out.println();
		// Line 16
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(23);
		out.print("*");
		spacing(3);
		out.print("*");
		spacing(15);
		out.print("*");
		out.println();
		// Line 17
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(5);
		out.print("*");
		spacing(13);
		out.print("*");
		out.println();
		// Line 18
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(21);
		out.print("*");
		spacing(7);
		out.print("*");
		spacing(11);
		out.print("*");
		out.println();
		// Line 19
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(20);
		out.print("*");
		spacing(9);
		out.print("*");
		spacing(9);
		out.print("*");
		out.println();
		// Line 20
		out.print("*");
		spacing(22);
		out.print("*");
		spacing(19);
		out.print("*");
		spacing(11);
		out.print("*");
		spacing(7);
		out.print("*");
		out.println();
		// Line 21
		for (int i = 0; i < 20; i++)
			out.print("*");
		spacing(3);
		for (int i = 0; i < 20; i++)
			out.print("*");
		out.print("*");
		spacing(12);
		for (int i = 0; i < 7; i++)
			out.print("*");
	}

	private static void spacing(int a) {
		for (int i = 0; i < a; i++)
			out.print(" ");
	}
}
