package objectOriented.investments;

import java.util.Random;

import static java.lang.Double.MIN_VALUE;
import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class BlueChipStock extends Investment {
	private final double minimumInterest;
	private final double maximumInterest;
	private final double collapseChance;

	BlueChipStock() {
		this(new Name("Blue Chip Stock"));
	}

	private BlueChipStock(Name name) {
		super(name);
		minimumInterest = -8;
		maximumInterest = 15;
		collapseChance = 1;
	}

	@SuppressWarnings("SameParameterValue")
	BlueChipStock(String name, double minimumInterest, double maximumInterest, double collapseChance) {
		super(new Name(name));
		this.minimumInterest = 100 * minimumInterest;
		this.maximumInterest = 100 * maximumInterest;
		this.collapseChance = 100 * collapseChance;
	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(
				((random() * ((maximumInterest - minimumInterest) + MIN_VALUE)) + minimumInterest) + "%");
		var chance = new Random().nextInt(100) + 1;
		double totalYield;
		if (chance >= 1 && chance <= collapseChance) {
			totalYield = -amount;
			addToYield(totalYield);
			amount = 0;
		} else {
			totalYield = amount * interestRate.doubleValue();
			addToYield(totalYield);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a %s yield of %s for a total of %s.%n", getName(), interestRate, format(totalYield),
				format(amount));
		return amount;
	}
}
