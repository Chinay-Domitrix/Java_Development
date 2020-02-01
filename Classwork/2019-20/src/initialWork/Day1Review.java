package initialWork;

import java.util.Scanner;

import static java.lang.Math.random;
import static java.lang.System.in;
import static java.lang.System.out;
import static java.util.stream.IntStream.*;

public class Day1Review {
	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(in)) {
			out.print("Please enter the program number, as listed on the PDF. ");
			switch (scanner.nextInt()) {
				case 1:
					int x = 0;
					int counter = 0;
					for (int i = 2; i <= 100; i += 2) {
						x += i;
						counter++;
					}
					out.println(x / counter);
					break;
				case 2:
					rangeClosed(1, 6).forEachOrdered(i -> {
						iterate(6, j -> j > i, j -> j - 1).mapToObj(j -> "*").forEachOrdered(out::print);
						out.println();
					});
					break;
				case 3:
					range(1, 5).forEachOrdered(i -> {
						iterate(5, j -> j > i, j -> j - 1).forEachOrdered(out::print);
						if (i % 2 == 1) out.print('*');
						out.println();
					});
					break;
				case 4:
					range(0, 15).map(i -> (int) (random() * 15) + 1).forEachOrdered(rand -> {
						range(0, rand).map(j -> '*').forEachOrdered(out::print);
						out.println();
					});
					break;
				case 5:
					range(0, 5).forEachOrdered(i -> {
						range(0, i).map(j -> '*').forEachOrdered(out::print);
						out.println(i);
						range(0, i).map(j -> '*').forEachOrdered(out::print);
						out.println(i + 1);
					});
					break;
				case 8:
					int x8 = 1, y8 = 2, z8, count = 20;
					for (int i = 1; i <= count; i++) {
						out.print(y8 + "\t");
						z8 = x8;
						//noinspection SuspiciousNameCombination
						x8 = y8;
						y8 += z8;
						if (i % 5 == 0) out.println();
					}
					break;
				case 9:
					out.print("What number do you want to go up to? ");
					int input = scanner.nextInt();
					rangeClosed(1, input).mapToObj(i -> "[" + i + ']').forEachOrdered(out::print);
					out.println();
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + scanner.nextInt());
			}
		}
	}
}