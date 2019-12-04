package initialWork;

import java.util.Scanner;

import static java.lang.System.out;

public class Day1Review {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("Please enter the program number, as listed on the PDF. ");
			switch (in.nextInt()) {
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
					for (int i = 1; i <= 6; i++) {
						for (int j = 6; j > i; j--) out.print("*");
						out.println();
					}
					break;
				case 3:
					for (int i = 1; i < 5; i++) {
						for (int j = 5; j > i; j--) out.print(j);
						if (i % 2 == 1) out.print('*');
						out.println();
					}
					break;
				case 4:
					for (int i = 0; i < 15; i++) {
						int rand = (int) (Math.random() * 15) + 1;
						for (int j = 0; j < rand; j++) out.print('*');
						out.println();
					}
					break;
				case 5:
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < i; j++) out.print('*');
						out.println(i);
						for (int j = 0; j < i; j++) out.print('*');
						out.println(i + 1);
					}
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
					int input = in.nextInt();
					for (int i = 1; i <= input; i++) out.print("[" + i + ']');
					out.println();
					break;
				default:
					throw new IllegalStateException("Unexpected value: " + in.nextInt());
			}
		}
	}
}