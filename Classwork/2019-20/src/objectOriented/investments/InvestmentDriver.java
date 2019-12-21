package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;
import static objectOriented.investments.Investment.format;

final class InvestmentDriver {
	private static final int age = 16;
	private static final int retirementAge = 65;
	private static ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	public static void main(String[] args) {
		portfolio.add(new CertificateOfDeposit());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(0).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(0));
		portfolio.add(new MutualFund());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(1).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(1));
		portfolio.add(new BlueChipStock());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(2).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(2));
		portfolio.add(new PennyStock());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(3).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(3));
		portfolio.add(new Moonshot());
		for (var i = age; i <= retirementAge; i++) total = portfolio.get(4).invest1Year(total);
		out.printf("%s%n%n", portfolio.get(4));
		out.printf("Total: %s%n", format(total));
	}
}