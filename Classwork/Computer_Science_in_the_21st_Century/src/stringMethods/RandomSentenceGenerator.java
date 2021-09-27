package stringMethods;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.System.out;

public class RandomSentenceGenerator {
	public static void main(final String[] args) {
		out.print("Please enter a sentence. ");
		String main;
		try (Scanner in = new Scanner(System.in)) {
			main = in.nextLine();
		}
		out.println();
		main = main.toUpperCase();
		final char[] punctuation = { '.', ',', '!', '?', ':' };
		for (final char c : punctuation)
			main = main.replace(c, ' ');
		final String[] y = main.replace("  ", " ").trim().split(" ");
		out.println("Word Count: " + y.length + "\nUnsorted Array: ");
		for (int i = 0; i < y.length; i++) {
			out.print(y[i] + "\t\t");
			if (i % 4 == 0 && i != 0)
				out.println();
		}
		out.println("\n\nSorted Array:");
		for (int i = 0; i < y.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < y.length; j++)
				if (y[j].compareToIgnoreCase(y[minIndex]) < 0)
					minIndex = j;
			final String ph = y[i];
			y[i] = y[minIndex];
			y[minIndex] = ph;
		}
		for (int i = 0; i < y.length; i++) {
			out.print(y[i] + "\t");
			if (i % 4 == 0 && i != 0)
				out.println();
		}
		out.println("\n");
		final int[] arr = { 1, 2, 3, 4, 5 };
		for (final int i : arr) {
			final String[] z = new String[y.length];
			String target = "";
			Arrays.fill(z, "0");
			boolean a = true;
			while (a) {
				final int index = (int) (random() * y.length);
				if (z[index].equals("0")) {
					z[index] = y[index];
					target = target.concat(y[index] + " ");
					a = false;
					for (final String s : z)
						if (s.equals("0")) {
							a = true;
							break;
						}
				}
			}
			out.println("New Sentence " + i + ":\n" + target);
		}
	}
}
