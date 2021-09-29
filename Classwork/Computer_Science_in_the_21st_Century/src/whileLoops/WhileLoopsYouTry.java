package whileLoops;

import java.util.Scanner;

import static java.lang.System.out;

final class WhileLoopsYouTry {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("Which sub-program do you want to run? ");
			int x, y = in.nextInt();
			switch (y) {
				case 1 -> {
					x = 1;
					while (x < 10) {
						out.print(x + " ");
						x++;
					}
				}
				case 2 -> {
					x = 6;
					while (x < 10) {
						out.print(x + " ");
						x++;
					}
				}
				case 3 -> {
					x = 10;
					while (x > 0) {
						out.print(x + " ");
						x--;
					}
				}
				case 4 -> {
					x = 0;
					while (x < 2) {
						out.print(x + " ");
						x++;
					}
				}
				case 5 -> {
					x = 5;
					while (x < 31) {
						out.print(x + " ");
						x += 5;
					}
				}
				default -> throw new IllegalStateException("Unexpected value: " + y);
			}
		}
	}
}
