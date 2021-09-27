package objectOriented.investments;

import java.util.Random;

import static java.lang.Double.MIN_VALUE;
import static java.lang.Math.random;
import static java.lang.System.out;
import static objectOriented.investments.InterestRate.doubleValue;

final class PennyStock extends Investment {
	private final int multiplier;
	private double minimumInterest = 100;
	private double maximumInterest = 100;
	private double collapseChance = 100;
	private double multiplierChance = 100;

	PennyStock() {
		this(new Name("Penny Stock"));
	}

	private PennyStock(Name name) {
		super(name);
		minimumInterest = -50;
		maximumInterest = 80;
		collapseChance = 5;
		multiplierChance = 5;
		multiplier = 50;
	}

	@SuppressWarnings("SameParameterValue")
	PennyStock(String name, double minimumInterest, double maximumInterest, double collapseChance,
			double multiplierChance, int multiplier) {
		super(new Name(name));
		this.minimumInterest *= minimumInterest;
		this.maximumInterest *= maximumInterest;
		this.collapseChance *= collapseChance;
		this.multiplierChance *= multiplierChance;
		this.multiplier = multiplier;
	}

	@Override
	double invest1Year(double amount) {
		var interestRate = new InterestRate(
				((random() * ((maximumInterest - minimumInterest) + MIN_VALUE)) + minimumInterest) + "%");
		var chance = new Random().nextInt(100) + 1;
		double yield;
		if (chance >= 1 && chance <= collapseChance) {
			yield = -amount;
			addToYield(yield);
			amount = 0;
		} else if (chance >= collapseChance + 1 && chance <= multiplierChance) {
			yield = amount * multiplier;
			addToYield(yield);
			amount *= multiplier;
		} else {
			yield = amount * interestRate.doubleValue();
			addToYield(yield);
			amount *= doubleValue(interestRate) + 1;
		}
		out.printf("%s returned a %s yield of %s for a total of %s.%n", getName(), interestRate, format(yield),
				format(amount));
		return amount;
	}
}
