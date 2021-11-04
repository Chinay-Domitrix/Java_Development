package whileLoops;

import java.util.Scanner;

import static java.lang.System.out;

class WhileAndForLoopConversions {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("Which program do you want to run? ");
			switch (in.nextInt()) {
				case 0 -> {
					out.println();
					for (int x = 1; x < 9; x++)
						out.printf("%dx%n", x);
				}
				case 1 -> {
					int z = 0;
					for (int y = 0; y < 5; y++)
						z += y;
					out.println(z);
				}
				case 2 -> {
					int j = 1;
					int i = 2;
					do {
						j *= i;
						i += 3;
					} while (i <= 12);
					out.println(j);
				}
				default -> throw new IllegalStateException("Unexpected value: " + in.nextInt());
			}
		}
	}
}
