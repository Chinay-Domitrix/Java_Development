package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;
import static objectOriented.investments.Investment.format;

public final class InvestmentDriver {
	private static final int age = 16;
	private static final int retirementAge = 65;
	private static ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	public static void main(String[] args) {
		portfolio.add(new CD());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(0).invest1Year(total);
		out.printf("%s%n%s%n%n", format(total), portfolio.get(0));
		portfolio.add(new MutualFund());
		for (var i = age; i <= retirementAge; i++) total += portfolio.get(1).invest1Year(total);
		out.printf("%s%n%s%n%n", format(total), portfolio.get(1));
		portfolio.add(new BlueChipStock());
		for (var i = age; i <= retirementAge; i++) total += portfolio.get(2).invest1Year(total);
		out.printf("%s%n%s%n%n", format(total), portfolio.get(2));
	}
}