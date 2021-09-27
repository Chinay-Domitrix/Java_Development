package whileLoops;

import java.util.Scanner;

import static java.lang.System.out;
import static java.util.stream.IntStream.range;

class CollatzSequence {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		out.print("Enter a number: ");
		int n = in.nextInt();
		int f = n;
		int h = 0;
		int counter = 0;
		while (n != 1) {
			counter++;
			if (n % 2 == 0)
				n /= 2;
			else {
				n *= 3;
				n += 1;
			}
			if (n > f) {
				f = n;
				h = counter;
			}
			out.printf("Step %d: %d\t", counter, n);
			if ((counter % 10) == 0)
				out.println();
		}
		range(0, 2).forEach(i -> out.println());
		out.printf("The largest number was %d, which occurred on Step %d.%n", f, h);
		in.close();
	}
}
