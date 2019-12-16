package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;
import static objectOriented.investments.Investment.format;

class InvestmentDriver {
	private static final int age = 16;
	private static final int retirementAge = 65;
	private static final ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	public static void main(String[] args) {
		portfolio.add(new CD());
		for (int j = age; j <= retirementAge; j++) total = portfolio.get(0).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(0));
		portfolio.add(new MutualFund());
		for (int j = age; j <= retirementAge; j++) total = portfolio.get(1).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(1));
		portfolio.add(new BlueChipStock());
		for (int j = age; j <= retirementAge; j++) total = portfolio.get(2).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(2));
		out.printf("Total: %s%n", format(total));
	}
}