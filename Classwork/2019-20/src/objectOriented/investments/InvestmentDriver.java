package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;

public final class InvestmentDriver {
	private static final int age = 16;
	private static final int retirementAge = 65;
	private static ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	public static void main(String[] args) {
		portfolio.add(new CD("CD with 2% yield", 0.02));
		for (var i = age; i <= retirementAge; i++) total += portfolio.get(0).invest1Year(total) - total;
		out.println(Investment.format(total));
		var inv = portfolio.get(0);
		out.println(inv);
	}
}