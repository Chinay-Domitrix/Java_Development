package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;

public class InvestmentSplitDriver {
	/**
	 * The the first year of investments
	 */
	private static final int age = 15;
	/**
	 * The last year of investments
	 */
	private static final int retirementAge = 65;
	/**
	 * The {@link ArrayList<Investment>} for all of the
	 */
	private static ArrayList<Investment> portfolio = new ArrayList<>();
	private static double total = 1000;

	/**
	 * Percent invested in a {@link CD} investment
	 */
	private static double cdP = 0.3;

	/**
	 * Percent invested in a {@link MutualFund} investment
	 */
	private static double mfP = 0.2;

	/**
	 * Percent invested in a {@link BlueChipStock} investment
	 */
	private static double bcP = 0.2;

	/**
	 * Percent invested in a {@link PennyStock} investment
	 */
	private static double psP = 0.29;

	/**
	 * Percent invested in a {@link Moonshot} investment
	 */
	private static double msP = 0.01;

	public static void main(String[] args) {
		portfolio.clear();  // Note you should clear these each time if you run multiple simulations
		portfolio.add(new CD("CD with 2% yield", 0.02)); // Investment 0
		portfolio.add(new MutualFund("Mutual Fund (-3% -> 8%)", -0.03, 0.08, 20)); // Investment 1
		portfolio.add(new BlueChipStock("Blue Chip Stock (-8% -> 15%)", -0.08, 0.15, 0.01)); // Investment 2
		portfolio.add(new PennyStock("Penny Stock (-50% -> 80%)", -0.5, 0.8, 0.05, 0.05, 50)); // Investment 3
		portfolio.add(new Moonshot("Moonshot (80% / 1%)", 0.8, 0.01, 100000)); // Investment 4

		// Execute Investment strategy for each year
		for (int i = age; i <= retirementAge; i++) {
			// MAKE ADJUSTMENTS BASED ON AGE AND/OR TOTAL
			if (age > 55) {
				cdP = 0.2;  // % CD
				mfP = 0.6;  // % Mutual Fund
				bcP = 0.2;  // % BlueChip Stock
				psP = 0.0; // % Penny Stock
				msP = 0.0; // % Moonshot
			}
			// This line makes the investment according to your current allocation
			total = splitInvestmentByPercent();
		}
		// Prints overall total at retirement on a separate line
		out.printf("%nOverall Total => %s%n", Investment.format(total));
//		Print the yield of each Investment
		for (var inv : portfolio)
			out.printf("Yield of %s from %s%n", Investment.format(inv.getTotalYield()), inv.getName());
	}

	// Use this method to split investment, but I don't suggest you change it.
	private static double splitInvestmentByPercent() {
		if ((cdP + mfP + bcP + psP + msP) > 1) { // The +0.0001 allows for a small rounding error
			out.println("You cannot invest more than 100%");
			return 0;
		}
		var a = new double[]{cdP, mfP, bcP, psP, msP};
		var localTotal = 0.0;
		for (var i = 0; i < a.length; i++) localTotal += portfolio.get(i).invest1Year(a[i] * total);
		return localTotal;
	}
}