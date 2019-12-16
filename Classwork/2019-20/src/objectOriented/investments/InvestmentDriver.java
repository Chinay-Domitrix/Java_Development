package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;
import static java.util.stream.IntStream.rangeClosed;
import static objectOriented.investments.Investment.format;

final class InvestmentDriver {
	private static final int age = 16;
	private static final int retirementAge = 65;
	private static final ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	public static void main(String[] args) {
		portfolio.add(new CD());
		portfolio.add(new MutualFund());
		portfolio.add(new BlueChipStock());
		for (var i : portfolio) {
			rangeClosed(age, retirementAge).forEachOrdered(j -> total = i.invest1Year(total));
			out.printf("%s%n%n", i);
		}
		out.printf("Total: %s%n", format(total));
	}
}