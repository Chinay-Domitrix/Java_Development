package switchStatements;

import java.util.Scanner;

import static java.lang.System.out;

class HolidayShopping {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			out.print("Regular Price: $");
			final double rprice = in.nextDouble();
			out.print("Department Code: ");
			final int code = in.nextInt();
			double sprice = switch (code) {
				case 310 -> rprice * 0.90;
				case 438 -> rprice * 0.88;
				case 652 -> rprice * 0.85;
				default -> rprice * 0.95;
			};
			out.println("Sale Price: $" + sprice);
		}
	}
}
