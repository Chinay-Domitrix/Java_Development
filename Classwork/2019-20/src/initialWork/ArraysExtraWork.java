package initialWork;

import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.System.out;
import static java.util.Arrays.setAll;

class ArraysExtraWork {
	private Scanner in = new Scanner(System.in);

	private ArraysExtraWork() {
		out.println("Program 4");
		out.println(program4());
		out.println("\nProgram 5\n" + program5());
		out.print("\nProgram 6\nPlease enter your array dimension number. ");
		program6();
	}

	public static void main(String[] args) {
		new ArraysExtraWork();
	}

	@NotNull
	private String program4() {
		int[] x = new int[100];
		setAll(x, i -> (int) (random() * 100) + 1);
		for (int i = 0; i < x.length - 1; i++) {
			int mindex = i;
			for (int j = i + 1; j < x.length; j++) if (x[j] < x[mindex]) mindex = j;
			int ph = x[i];
			x[i] = x[mindex];
			x[mindex] = ph;
		}
		out.print("Define \"k\" in \"kth smallest term\": ");
		return "The number is " + x[in.nextInt() - 1];
	}

	@NotNull
	private String program5() {
		int[] x = new int[100];
		setAll(x, i -> (int) (random() * 100) + 1);
		int counter = 1;
		for (int i = 1; i < x.length; i++)
			if (x[i] != x[i - 1]) {
				counter = 0;
				counter++;
			} else if (x[i] == x[i - 1]) counter++;
		return "The longest consecutive number chain was " + counter + " numbers long.";
	}

	private void program6() {
		int n = in.nextInt(), z = 0;
		int[][] x = new int[n][n];
		int[] y = new int[n * n];
		for (int[] value : x) setAll(value, input -> (int) (random() * 1000) + 1);
		for (int[] value : x)
			for (int i : value) {
				y[z] = i;
				z++;
			}
		for (int i = 0; i < y.length - 1; i++) {
			int mindex = i;
			for (int j = i + 1; j < y.length; j++) if (y[j] < y[mindex]) mindex = j;
			int ph = y[i];
			y[i] = y[mindex];
			y[mindex] = ph;
		}
		z = 0;
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < x[i].length; j++) {
				x[i][j] = y[z];
				z++;
			}
		for (int[] ints : x) {
			for (int anInt : ints) out.print(anInt + "\t");
			out.println();
		}
	}
}