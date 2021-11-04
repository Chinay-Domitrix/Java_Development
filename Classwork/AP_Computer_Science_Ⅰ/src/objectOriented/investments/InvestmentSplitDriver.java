package objectOriented.investments;

import java.util.ArrayList;

import static java.lang.System.out;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static objectOriented.investments.Investment.format;

public class InvestmentSplitDriver {
	/**
	 * The the first year of investments
	 */
	private static final int age = 16;

	/**
	 * The last year of investments
	 */
	private static final int retirementAge = 65;

	/**
	 * The {@linkplain ArrayList<Investment>} for all of the types of investments.
	 * <p>
	 * Note: the {@linkplain ArrayList} mus be filled in the order of
	 * {@linkplain CertificateOfDeposit}, {@linkplain MutualFund},
	 * {@linkplain BlueChipStock}, {@linkplain PennyStock}, and lastly
	 * {@linkplain Moonshot} in order for the method
	 * {@link #splitInvestmentByPercent()} to work.
	 */
	private static final ArrayList<Investment> portfolio = new ArrayList<>();

	/**
	 * The initial amount of money
	 */
	private static double total = 1000;

	/**
	 * Percent invested in a {@link CertificateOfDeposit} investment
	 */
	private static double cdP = 0.30125;

	/**
	 * Percent invested in a {@link MutualFund} investment
	 */
	private static double mfP = 0.20125;

	/**
	 * Percent invested in a {@link BlueChipStock} investment
	 */
	private static double bcP = 0.20125;

	/**
	 * Percent invested in a {@link PennyStock} investment
	 */
	private static double psP = 0.29125;

	/**
	 * Percent invested in a {@link Moonshot} investment
	 */
	private static double msP = 0.005;

	public static void main(String[] args) {
		// Note you should clear these each time if you run multiple simulations
		portfolio.clear();
		portfolio.add(new CertificateOfDeposit("CD with 2% yield", 0.02)); // Investment 0
		portfolio.add(new MutualFund("Mutual Fund (-3% -> 8%)", -0.03, 0.08, 20)); // Investment 1
		portfolio.add(new BlueChipStock("Blue Chip Stock (-8% -> 15%)", -0.08, 0.15, 0.01)); // Investment 2
		portfolio.add(new PennyStock("Penny Stock (-50% -> 80%)", -0.5, 0.8, 0.05, 0.05, 50)); // Investment 3
		portfolio.add(new Moonshot("Moonshot (80% / 1%)", 0.8, 0.01, 100000)); // Investment 4

		// Execute Investment strategy for each year
		rangeClosed(age, retirementAge).forEachOrdered(i -> {
			// MAKE ADJUSTMENTS BASED ON AGE AND/OR TOTAL
			if ((age > 55) || (total >= 500000)) {
				// Percent invested in a Certificate of Deposit investment
				cdP = 0.2;
				// Percent invested in a Mutual Fund investment
				mfP = 0.6;
				// Percent invested in a Blue Chip Stock investment
				bcP = 0.2;
				// Percent invested in a Penny Stock investment
				psP = 0.0;
				// Percent invested in a Moonshot investment
				msP = 0;
			}
			// This line makes the investment according to your current allocation
			total = splitInvestmentByPercent();
			out.println();
		});
		// Prints overall total at retirement on a separate line
		out.printf("%nOverall Total => %s%n", format(total));
		// Print the yield of each Investment
		portfolio.forEach(inv -> out.printf("Yield of %s from %s%n", format(inv.getTotalYield()), inv.getName()));
	}

	/**
	 * Used to split an investment
	 *
	 * @see Investment#invest1Year(double)
	 */
	private static double splitInvestmentByPercent() {
		// The +0.0001 allows for a small rounding error
		if ((cdP + mfP + bcP + psP + msP) > 1) {
			out.println("You cannot invest more than 100%");
			return 0;
		}
		var a = new double[]{cdP, mfP, bcP, psP, msP};
		return range(0, a.length).map(i -> (int) portfolio.get(i).invest1Year(a[i] * total)).sum();
	}
}
