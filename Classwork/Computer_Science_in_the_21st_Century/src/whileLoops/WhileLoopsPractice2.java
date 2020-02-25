package whileLoops;

import static java.lang.System.out;

class WhileLoopsPractice2 {
	public static void main(final String[] args) {
		int x = 1;
		int y = 10;
		int counter = 0;
		while (y > 0) {
			if (x % 2 == 0) y /= x + 1;
			else
				//noinspection SuspiciousNameCombination
				y += x;
			x++;
			counter++;
		}
		out.println(counter);
	}
}
