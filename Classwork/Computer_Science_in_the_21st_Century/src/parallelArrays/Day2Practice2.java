package parallelArrays;

import java.util.Scanner;

import static java.lang.System.out;

public class Day2Practice2 {
	public static void main(final String[] args) {
		final Scanner in = new Scanner(System.in);
		print("How many people are you doing this for? ");
		final int bounds = in.nextInt();
		int i = 0;
		// noinspection MismatchedReadAndWriteOfArray
		final String[] name = new String[bounds];
		final int[] age = new int[bounds], feet = new int[bounds];
		final double[] weight = new double[bounds], inches = new double[bounds], avg = new double[4];
		final int[] intSto = {0, 0};
		final double[] doubleSto = {0.0, 0.0};
		in.nextLine();
		do {
			print("Please enter a person's name. ");
			name[i] = in.nextLine();
			print("Please enter their age. ");
			age[i] = in.nextInt();
			print("Please enter the feet part of their height. ");
			feet[i] = in.nextInt();
			print("Please enter the inches part of their height. ");
			inches[i] = in.nextDouble();
			if (inches[i] > 12) {
				feet[i] += (int) inches[i] / 12;
				inches[i] %= 12;
			}
			print("Please enter their weight in pounds. ");
			weight[i] = in.nextDouble();
			in.nextLine();
			intSto[0] += age[i];
			intSto[1] += feet[i];
			doubleSto[0] += inches[i];
			doubleSto[1] += weight[i];
			i++;
		} while (i != bounds);
		avg[0] = (double) intSto[0] / i;
		avg[1] = (double) intSto[1] / i;
		avg[2] = doubleSto[0] / i;
		avg[3] = doubleSto[1] / i;
		if (avg[1] - (int) avg[1] != 0) {
			avg[2] += 12 * (avg[1] - (int) avg[1]);
			avg[1] = (int) avg[1];
		}
		if (avg[2] > 12) {
			avg[1] += (int) avg[2] / 12;
			avg[2] %= 12;
		}
		println("The average age was roughly " + Math.round(avg[0]) + " years. The average height was around "
				+ Math.round(avg[1]) + " feet, " + Math.round(avg[2])
				+ " inches. Finally, the average weight was approximately " + Math.round(avg[3]) + " pounds.");
		in.close();
	}

	private static void print(final String a) {
		out.print(a);
	}

	private static void println(final String a) {
		print(a + "\n");
	}
}
