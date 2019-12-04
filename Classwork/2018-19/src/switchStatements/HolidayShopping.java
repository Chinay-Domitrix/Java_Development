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
			double sprice;
			switch (code) {
				case 310:
					sprice = rprice * 0.90;
					break;
				case 438:
					sprice = rprice * 0.88;
					break;
				case 652:
					sprice = rprice * 0.85;
					break;
				default:
					sprice = rprice * 0.95;
					break;
			}
			out.println("Sale Price: $" + sprice);
		}
	}
}