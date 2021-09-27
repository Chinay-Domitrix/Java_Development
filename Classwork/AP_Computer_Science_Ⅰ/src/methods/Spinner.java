package methods;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static java.lang.Math.*;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.lang.Thread.sleep;
import static java.util.Arrays.sort;

class Spinner {
	private Spinner() {
		int a = spin(2);
		boolean even = isEven(a);
		int s1 = spin(), s2 = spin(), s3, s4;
		out.printf("Spin 1: %d\tSpin 2: %d\t", a, s2);
		if (even) {
			s3 = spin(4);
			out.printf("Spin 3: %d\tSpin 4: %d%n", s2, s3);
			boolean b = false;
			int c = lowest(s1, s2);
			if (c == s1)
				b = isFirstGreaterOrEqual(product(c, s3), (int) pow(s2, 2));
			else if (c == s2)
				b = isFirstGreaterOrEqual(product(c, s3), (int) pow(s1, 2));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println(result(b));
		} else {
			s3 = spin();
			s4 = spin(10);
			out.printf("Spin 3: %d\tSpin 4: %d\tSpin 5: %d%n", s2, s3, s4);
			int[] b = lowest(s1, s2, s3);
			boolean[] c = new boolean[3];
			c[0] = (b[0] == s1) || (b[1] == s1);
			c[1] = (b[0] == s2) || (b[1] == s2);
			c[2] = (b[0] == s3) || (b[1] == s3);
			boolean d = false;
			if (!c[0])
				d = isFirstGreaterOrEqual((b[0] * b[1]), (s1 * s4));
			else if (!c[1])
				d = isFirstGreaterOrEqual(product(b[0], b[1]), product(s2, s4));
			else if (!c[2])
				d = isFirstGreaterOrEqual(product(b[0], b[1]), product(s3, s4));
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			out.println(result(d));
		}
	}

	public static void main(String[] args) {
		new Spinner();
	}

	private int spin() {
		return (int) (random() * 5) + 1;
	}

	private int spin(int a) {
		return (int) (random() * a) + 1;
	}

	@Contract(pure = true)
	private int product(int a, int b) {
		return a * b;
	}

	@Contract(pure = true)
	private boolean isEven(int a) {
		return a % 2 == 0;
	}

	@Contract(pure = true)
	private int lowest(int a, int b) {
		return min(a, b);
	}

	@NotNull
	@Contract("_, _, _ -> new")
	private int[] lowest(int a, int b, int c) {
		int[] x = { a, b, c };
		sort(x);
		return new int[] { x[1], x[2] };
	}

	@Contract(pure = true)
	private boolean isFirstGreaterOrEqual(int a, int b) {
		return a >= b;
	}

	@NotNull
	@Contract(pure = true)
	private String result(boolean a) {
		return format("You %s!", a ? "win" : "lose");
	}
}
